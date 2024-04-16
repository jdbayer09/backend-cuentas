package com.jdbayer.cuentas.api.repositories;

import com.jdbayer.cuentas.api.models.entities.core.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByActiveIsTrueAndUser_Id(Long id);
    List<CategoryEntity> findAllByUser_Id(Long id);
}
