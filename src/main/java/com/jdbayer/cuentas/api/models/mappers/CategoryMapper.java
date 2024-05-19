package com.jdbayer.cuentas.api.models.mappers;

import com.jdbayer.cuentas.api.models.dto.core.CategoryDTO;
import com.jdbayer.cuentas.api.models.entities.core.CategoryEntity;
import com.jdbayer.cuentas.api.models.responses.core.BaseCategoryResponse;
import com.jdbayer.cuentas.api.models.responses.core.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    BaseCategoryResponse dtoToBaseResponse(CategoryDTO dto);
    CategoryResponse dtoToResponse(CategoryDTO dto);
    CategoryDTO entityToDto(CategoryEntity entity);
}
