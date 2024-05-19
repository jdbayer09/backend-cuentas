package com.jdbayer.cuentas.api.models.entities;

import com.jdbayer.cuentas.api.models.entities.admin.UserEntity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseUserEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 5588701708703604540L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;
}
