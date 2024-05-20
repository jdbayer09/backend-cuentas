package com.jdbayer.cuentas.api.models.entities.core;

import com.jdbayer.cuentas.api.models.entities.BaseUserEntity;
import com.jdbayer.cuentas.api.models.enums.Color;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "cash_receipts", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "id_user", "month", "year"}, name = "cash_receipts_unique_name")
})
@Getter
@Setter
@ToString
public class CashReceiptEntity extends BaseUserEntity {


    @Serial
    private static final long serialVersionUID = -1910357017255211783L;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "color", length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2, columnDefinition = "NUMERIC")
    private BigDecimal amount;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "is_paid", nullable = false)
    private boolean paid;
}
