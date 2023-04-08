package com.example.projectliboo.service.User;

import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.model.entity.Role;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.repository.RoleRepository;
import com.example.projectliboo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl( RoleRepository repository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        Role role = roleRepository.findByRoleName(RoleEnum.USER);
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
