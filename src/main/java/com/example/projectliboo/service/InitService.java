package com.example.projectliboo.service;

import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InitService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            initAdmin();
        }
    }

    private void initAdmin() {

        User user = new User();
        user.setUsername("admin");
        user.setRole(RoleEnum.ADMIN);
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setEmail("admin@gmail.com");
        userRepository.save(user);

    }

}

