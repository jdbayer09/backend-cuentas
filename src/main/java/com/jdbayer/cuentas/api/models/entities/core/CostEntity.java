package com.jdbayer.cuentas.api.models.entities.core;

import com.jdbayer.cuentas.api.models.entities.BaseUserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.math.BigDecimal;

@Entity
@Table(name = "costs")
@Getter
@Setter
@ToString
public class CostEntity extends BaseUserEntity {
    @Serial
    private static final long serialVersionUID = -7113102157100073297L;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2, columnDefinition = "NUMERIC")
    private BigDecimal amount;

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "is_paid", nullable = false)
    private boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category", nullable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_payment_method", nullable = false)
    private PaymentMethodEntity paymentMethod;
}
