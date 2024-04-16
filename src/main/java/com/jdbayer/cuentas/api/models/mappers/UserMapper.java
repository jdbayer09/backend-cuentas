package com.jdbayer.cuentas.api.models.mappers;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.admin.UserDetailDTO;
import com.jdbayer.cuentas.api.models.entities.admin.UserEntity;
import com.jdbayer.cuentas.api.models.responses.admin.UserBaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDetailDTO entityToDetailDto(UserEntity userEntity);

    UserBaseResponse dtoToUserBaseResponse(UserDetailDTO userDetailDTO);

    UserBaseResponse dtoToUserBaseResponse(UserDTO userDTO);

    UserDTO entityToDto(UserEntity userEntity);
}
