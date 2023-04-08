package com.example.projectliboo.service;

import com.example.projectliboo.model.entity.Role;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitService {

    private final RoleRepository roleRepository;

    public InitService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
    }
    private void initRoles() {

        if (roleRepository.count() == 0) {
            Arrays.stream(RoleEnum.values()).
                    map(roleEnum -> {
                        Role role = new Role();
                        role.setRoleName(roleEnum);

                        return role;
                    }).forEach(roleRepository::save);
        }
    }
}
