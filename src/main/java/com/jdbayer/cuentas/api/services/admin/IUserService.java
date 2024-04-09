package com.jdbayer.cuentas.api.services.admin;

import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;

public interface IUserService {
    UserDetailDTO getUserDetailByEmail(String email);
}
