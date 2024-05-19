package com.jdbayer.cuentas.api.models.dto.admin;

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
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5829632493766418214L;

    private Long id;
    private String name;
    private String lastName;
    private String fullName;
    private String email;
    private String pass;
    private String profileImageUrl;
    private boolean active;
    private boolean resetPass;
    private ZonedDateTime createdAt;
}
