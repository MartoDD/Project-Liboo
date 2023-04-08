package com.example.projectliboo.repository;

import com.example.projectliboo.model.entity.Role;
import com.example.projectliboo.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByRoleName (RoleEnum roleEnum);
}
