package com.jdbayer.cuentas.api.services.admin;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.requests.admin.RegisterUserRequest;

public interface IUserService {
    UserDetailDTO getUserDetailByEmail(String email);
    UserDTO registerUser(RegisterUserRequest registerUserRequest);
}
