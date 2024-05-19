package com.jdbayer.cuentas.api.repositories;

import com.jdbayer.cuentas.api.models.entities.core.CashReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICashReceiptRepository extends JpaRepository<CashReceiptEntity, Long> {
}
