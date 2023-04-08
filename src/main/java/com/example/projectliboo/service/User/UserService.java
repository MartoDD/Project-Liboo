package com.example.projectliboo.service.User;

import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.model.entity.User;

import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);
    Optional<User> findUserByEmail(String email);
    Optional<User>findUserByUsername(String username);
}
