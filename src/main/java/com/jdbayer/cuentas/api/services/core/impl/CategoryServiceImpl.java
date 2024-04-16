package com.jdbayer.cuentas.api.services.core.impl;

import com.jdbayer.cuentas.api.exceptions.core.NotExistCategoryException;
import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.dto.core.CategoryDTO;
import com.jdbayer.cuentas.api.models.entities.core.CategoryEntity;
import com.jdbayer.cuentas.api.models.mappers.CategoryMapper;
import com.jdbayer.cuentas.api.models.mappers.UserMapper;
import com.jdbayer.cuentas.api.models.requests.core.CategoryRequest;
import com.jdbayer.cuentas.api.repositories.ICategoryRepository;
import com.jdbayer.cuentas.api.services.core.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    private final UserMapper userMapper;
    private static final String NOT_EXIST_MESSAGE = "No existe la categoría";

    @Override
    @Transactional
    public CategoryDTO createCategory(UserDTO user, CategoryRequest request) {
        var entity = new CategoryEntity();
        return createOrUpdateCategory(entity, request, user);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(UserDTO user, CategoryRequest request, Long idCategory) {
        var entity = categoryRepository.findById(idCategory).orElseThrow(() -> new NotExistCategoryException(NOT_EXIST_MESSAGE));
        return createOrUpdateCategory(entity, request, user);
    }

    private CategoryDTO createOrUpdateCategory(CategoryEntity category, CategoryRequest request, UserDTO user) {
        category.setUser(userMapper.dtoToEntity(user));
        category.setName(request.getName().toUpperCase().trim());
        category.setDescription(request.getDescription().trim());
        category.setIcon(request.getIcon().toLowerCase().trim());
        category.setColor(request.getColor().toUpperCase().trim());
        return mapper.entityToDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void disableCategory(Long idCategory) {
        var entity = categoryRepository.findById(idCategory).orElseThrow(() -> new NotExistCategoryException(NOT_EXIST_MESSAGE));
        entity.setActive(false);
        categoryRepository.save(entity);
    }

    @Override
    @Transactional
    public void enableCategory(Long idCategory) {
        var entity = categoryRepository.findById(idCategory).orElseThrow(() -> new NotExistCategoryException(NOT_EXIST_MESSAGE));
        entity.setActive(true);
        categoryRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAllCategories(UserDTO user) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getActiveCategories(UserDTO user) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long idCategory) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getActiveCategoryById(Long idCategory) {
        return null;
    }

}
