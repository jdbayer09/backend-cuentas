package com.jdbayer.cuentas.api.models.dto.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.enums.Color;
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
public class PaymentMethodDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2008904152552927962L;

    private Long id;
    private ZonedDateTime createdAt;
    private String name;
    private Color color;
    private String icon;
    private String description;
    private UserDTO user;
    private boolean active;
    private int paymentDate;
}
