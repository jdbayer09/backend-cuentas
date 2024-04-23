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

@Entity
@Table(name = "categories", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "id_user"}, name = "category_unique_name")
})
@Getter
@Setter
@ToString
public class CategoryEntity extends BaseUserEntity {

    @Serial
    private static final long serialVersionUID = -1900890954992882033L;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "color", length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    private Color color;

    @Column(name = "icon", length = 20, nullable = false)
    private String icon;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;
}
