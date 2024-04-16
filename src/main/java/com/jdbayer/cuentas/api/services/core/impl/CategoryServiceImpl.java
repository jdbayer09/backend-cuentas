package com.jdbayer.cuentas.api.services.core.impl;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CategoryDTO;
import com.jdbayer.cuentas.api.models.requests.core.CategoryRequest;
import com.jdbayer.cuentas.api.services.core.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    @Override
    public CategoryDTO createCategory(UserDTO user, CategoryRequest request) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(UserDTO user, CategoryRequest request, Long idCategory) {
        return null;
    }

    @Override
    public void disableCategory(Long idCategory) {

    }

    @Override
    public void enableCategory(Long idCategory) {

    }

    @Override
    public List<CategoryDTO> getAllCategories(UserDTO user) {
        return List.of();
    }

    @Override
    public List<CategoryDTO> getActiveCategories(UserDTO user) {
        return List.of();
    }

}
