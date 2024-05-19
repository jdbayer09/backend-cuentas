package com.jdbayer.cuentas.api.models.dto.admin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class UserDetailDTO implements Serializable, UserDetails {

    @Serial
    private static final long serialVersionUID = 1953526131674205880L;

    private Long id;
    private String name;
    private String lastName;
    private String fullName;
    private String email;
    private String pass;
    private String profileImageUrl;
    private boolean active;
    private ZonedDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.getPass();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
