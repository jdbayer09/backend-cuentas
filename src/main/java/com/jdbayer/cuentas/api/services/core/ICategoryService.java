package com.jdbayer.cuentas.api.services.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CategoryDTO;
import com.jdbayer.cuentas.api.models.requests.core.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    CategoryDTO createCategory(UserDTO user, CategoryRequest request);
    CategoryDTO updateCategory(UserDTO user, CategoryRequest request, Long idCategory);
    void disableCategory(UserDTO user, Long idCategory);
    void enableCategory(UserDTO user, Long idCategory);
    List<CategoryDTO> getAllCategories(UserDTO user);
    List<CategoryDTO> getActiveCategories(UserDTO user);
    CategoryDTO getCategoryById(UserDTO user, Long idCategory);
    CategoryDTO getActiveCategoryById(UserDTO user, Long idCategory);
}
