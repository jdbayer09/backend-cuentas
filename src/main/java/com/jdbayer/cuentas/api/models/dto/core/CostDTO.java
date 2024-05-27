package com.jdbayer.cuentas.api.models.dto.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@Builder
public class CostDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4157779004788138582L;

    private Long id;
    private ZonedDateTime createdAt;
    private UserDTO user;
    private String name;
    private BigDecimal amount;
    private Integer month;
    private Integer year;
    private boolean paid;
    private CategoryDTO category;
    private PaymentMethodDTO paymentMethod;
}
