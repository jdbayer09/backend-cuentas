package com.jdbayer.cuentas.api.services.admin.impl;

import com.jdbayer.cuentas.api.exceptions.admin.NotExistUserException;
import com.jdbayer.cuentas.api.exceptions.admin.NotPasswordEqualsException;
import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.entities.admin.UserEntity;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.admin.RegisterUserRequest;
import com.jdbayer.cuentas.api.repositories.IUserRepository;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



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

        return userMapper.entityToDto(resp);
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
