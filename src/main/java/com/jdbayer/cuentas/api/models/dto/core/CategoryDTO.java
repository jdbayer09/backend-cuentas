package com.jdbayer.cuentas.api.models.dto.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Builder
public class CategoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6506028125695993399L;

    private Long id;
    private ZonedDateTime createdAt;
    private String name;
    private String color;
    private String icon;
    private String description;
    private UserDTO user;
    private boolean active;
}
