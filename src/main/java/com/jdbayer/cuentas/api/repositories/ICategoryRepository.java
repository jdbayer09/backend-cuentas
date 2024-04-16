package com.jdbayer.cuentas.api.repositories;

import com.jdbayer.cuentas.api.models.entities.core.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByActiveIsTrueAndUser_IdOrderByName(Long id);
    List<CategoryEntity> findAllByUser_IdOrderByName(Long id);
    Optional<CategoryEntity> findByIdAndUser_Id(Long id, Long userId);
}
