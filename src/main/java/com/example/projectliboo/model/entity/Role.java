package com.example.projectliboo.model.entity;

import com.example.projectliboo.model.enums.RoleEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;
    @OneToMany
    private List<User> users;

    public Role() {
    }

    public RoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }
}
