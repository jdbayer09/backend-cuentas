package com.jdbayer.cuentas.api.models.dto.core;

import com.jdbayer.cuentas.api.models.dto.admin.UserDTO;
import com.jdbayer.cuentas.api.models.enums.Color;
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
public class CashReceiptDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4280472920751030220L;

    private Long id;
    private ZonedDateTime createdAt;
    private UserDTO user;
    private String name;
    private Color color;
    private BigDecimal amount;
    private Integer month;
    private Integer year;
    private boolean paid;
}
