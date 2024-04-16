package com.jdbayer.cuentas.api.services.admin;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.requests.admin.RegisterUserRequest;
import com.jdbayer.cuentas.api.models.requests.admin.ResetPassRequest;

public interface IUserService {
    UserDetailDTO getUserDetailByEmail(String email);
    UserDTO registerUser(RegisterUserRequest registerUserRequest);
    UserDTO activateUser(String activationCode);
    UserDTO forgotPassword(String email);
    UserDTO resetPassword(String code, ResetPassRequest request);
    UserDTO getUserById(Long id);
}
