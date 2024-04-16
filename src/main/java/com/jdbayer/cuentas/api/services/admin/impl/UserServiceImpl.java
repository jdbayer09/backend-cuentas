package com.jdbayer.cuentas.api.services.admin.impl;

import com.jdbayer.cuentas.api.exceptions.admin.NotExistUserException;
import com.jdbayer.cuentas.api.exceptions.admin.NotPasswordEqualsException;
import com.jdbayer.cuentas.api.exceptions.util.EmailException;
import com.jdbayer.cuentas.api.exceptions.util.NotEncryptException;
import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.entities.admin.UserEntity;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.admin.RegisterUserRequest;
import com.jdbayer.cuentas.api.repositories.IUserRepository;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import com.jdbayer.cuentas.api.services.util.IEncryptService;
import com.jdbayer.cuentas.api.services.util.IMailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final IMailService mailService;
    private final IEncryptService encryptService;

    @Value("${cuentas.frontend.host}")
    private String frontHost;

    @Override
    @Transactional(readOnly = true)
    public UserDetailDTO getUserDetailByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotExistUserException("El usuario con el correo electrónico (" + email + ") no existe")
                );
        return userMapper.entityToDetailDto(user);
    }

    @Override
    @Transactional
    public UserDTO registerUser(RegisterUserRequest registerUserRequest) {
        if (!registerUserRequest.getPassword().equals(registerUserRequest.getConfirmPassword()))
            throw new NotPasswordEqualsException("Las contraseñas no coinciden");

        UserEntity entity = new UserEntity();
        entity.setActive(false);
        entity.setEmail(registerUserRequest.getEmail().toLowerCase());
        entity.setName(capitaliceString(registerUserRequest.getName()));
        entity.setLastName(capitaliceString(registerUserRequest.getLastName()));
        entity.setPass(passwordEncoder.encode(registerUserRequest.getPassword()));
        var resp = userRepository.save(entity);
        var code = "";
        try {
            code = encryptService.encrypt(resp.getId().toString());
        } catch (NotEncryptException ex) {
            throw new EmailException("No se pudo registrar su usuario, intente de nuevo mas tarde", ex);
        }
        var body = "Hola, " + resp.getFullName() + "\n\n";
        body += "Para poder continuar con la aplicacion es necesario que active su cuenta en el siguiente enlace: \n \n";
        body += frontHost + "p/activate/"+code;

        try {
            mailService.sendMail(resp.getEmail(), "Activación de cuenta", body);
        } catch (MessagingException ex) {
            throw new EmailException("No se pudo registrar su usuario, intente de nuevo mas tarde", ex);
        }
        return userMapper.entityToDto(resp);
    }

    @Override
    @Transactional
    public UserDTO activateUser(String activationCode) {
        var code = "";
        try {
            code = encryptService.decrypt(activationCode);
        } catch (NotEncryptException ex) {
            throw new EmailException("No se pudo activar su usuario, intente de nuevo mas tarde", ex);
        }
        Long idUser = Long.parseLong(code);
        var user = userRepository.findById(idUser).orElseThrow(() -> new NotExistUserException("El usuario no existe"));
        if (user.isActive())
            throw new NotExistUserException("El usuario ya esta activado");
        user.setActive(true);
        return userMapper.entityToDto(userRepository.save(user));
    }

    private String capitaliceString(String val) {
        if (val == null || val.isEmpty()) return val;
        StringBuilder resp = new StringBuilder();
        var values =  val.toLowerCase().trim().split(" ");
        for (String newName: values) {
            resp.append(StringUtils.capitalize(newName)).append(" ");
        }
        return resp.toString().trim();
    }
}
