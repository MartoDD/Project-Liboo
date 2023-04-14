package com.example.projectliboo.service;

import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.GenreEnum;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.repository.GenreRepository;
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

    public InitService(UserRepository userRepository, PasswordEncoder passwordEncoder, GenreRepository genreRepository, GenreService genreService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.genreRepository = genreRepository;
        this.genreService = genreService;
    }

    @PostConstruct
    public void init() {
        initAll();
    }

    private void initAll(){
        if (userRepository.count()==0){
            initAdmin();
        }

        if (genreRepository.count()==0){
            initGenres();
        }

    }

    private void initAdmin() {

        User user = new User();
        user.setFullName("Admin Adminov");
        user.setUsername("admin");
        user.setRole(RoleEnum.ADMIN);
        user.setPassword(passwordEncoder.encode("admin123"));
        user.setEmail("admin@gmail.com");
        userRepository.save(user);

    }

    private void initGenres(){

        Arrays.stream(GenreEnum.values()).forEach(genreService::createGenre);

    }

}

