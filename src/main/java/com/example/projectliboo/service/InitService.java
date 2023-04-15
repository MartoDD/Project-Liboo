package com.example.projectliboo.service;

import com.example.projectliboo.model.entity.Role;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.repository.GenreRepository;
import com.example.projectliboo.repository.RoleRepository;
import com.example.projectliboo.repository.UserRepository;
import com.example.projectliboo.service.Genre.GenreService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class InitService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GenreRepository genreRepository;
    private final GenreService genreService;
    private final RoleRepository roleRepository;

    public InitService(UserRepository userRepository, PasswordEncoder passwordEncoder, GenreRepository genreRepository, GenreService genreService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.genreRepository = genreRepository;
        this.genreService = genreService;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        initAll();
    }

    private void initAll(){

        if (roleRepository.count()==0){
            initRoles();
        }
        
        if (userRepository.count()==0){
            initAdmin();
        }
    }

    private void initRoles(){

        Arrays.stream(RoleEnum.values()).forEach(this::createRole);
    }

    private void createRole(RoleEnum roleEnum){

        Role role=new Role();
        role.setRoleName(roleEnum);
        roleRepository.save(role);
    }

    private void initAdmin() {

        User user = new User();
        Role role = roleRepository.findByRoleName(RoleEnum.ADMIN);
        user.setFullName("Admin Adminov");
        user.setUsername("admin");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setEmail("admin@gmail.com");
        userRepository.save(user);

    }



}

