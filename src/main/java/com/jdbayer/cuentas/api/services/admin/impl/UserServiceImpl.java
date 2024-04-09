package com.jdbayer.cuentas.api.services.admin.impl;

import com.jdbayer.cuentas.api.exceptions.admin.NotExistUserException;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.repositories.IUserRepository;
import com.jdbayer.cuentas.api.services.admin.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;



    @Override
    @Transactional(readOnly = true)
    public UserDetailDTO getUserDetailByEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new NotExistUserException("El usuario con el correo electrónico (" + email + ") no existe")
                );
        return userMapper.entityToDetailDto(user);
    }
}
