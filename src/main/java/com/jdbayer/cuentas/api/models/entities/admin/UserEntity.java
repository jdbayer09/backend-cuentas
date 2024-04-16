package com.jdbayer.cuentas.api.models.entities.admin;

import com.jdbayer.cuentas.api.models.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "user_unique_email")
})
@Getter
@Setter
@ToString
public class UserEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -3503423029184150882L;

    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @Column(name = "last_name", length = 60, nullable = false)
    private String lastName;

    @Column(name = "email", length = 120, nullable = false)
    private String email;

    @Column(name = "password", length = 200, nullable = false)
    private String pass;

    @Column(name = "image_profile_url", length = 500)
    private String profileImageUrl;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    public String getFullName(){
        return this.name + " " + this.lastName;
    }
}
