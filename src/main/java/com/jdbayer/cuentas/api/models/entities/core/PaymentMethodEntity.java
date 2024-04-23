package com.jdbayer.cuentas.api.models.entities.core;

import com.jdbayer.cuentas.api.models.entities.BaseEntity;
import com.jdbayer.cuentas.api.models.entities.admin.UserEntity;
import com.jdbayer.cuentas.api.models.enums.Color;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "payment_methods", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "id_user"}, name = "payment_methods_unique_name")
})
@Getter
@Setter
@ToString
public class PaymentMethodEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 5046404703385700084L;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "color", length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "icon", length = 20, nullable = false)
    private String icon;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "user_id_payment_methods_fk"))
    private UserEntity user;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}
