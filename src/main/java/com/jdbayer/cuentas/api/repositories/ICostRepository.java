package com.jdbayer.cuentas.api.repositories;

import com.jdbayer.cuentas.api.models.entities.core.CostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICostRepository extends JpaRepository<CostEntity, Long> {
    Optional<CostEntity> findByIdAndUser_Id(Long id, Long userId);

    @Query("SELECT cr FROM CostEntity cr WHERE cr.user.id = ?1 AND cr.month = ?2 AND cr.year = ?3 ORDER BY cr.paid")
    List<CostEntity> fetchAllFilter(Long id, int month, int year);
}
