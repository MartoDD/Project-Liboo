package com.example.projectliboo.service.User;

import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.model.dtos.UserSearchDto;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.model.view.UserView;
import com.example.projectliboo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setFullName(userRegisterDto.getFullName());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(RoleEnum.USER);
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

    @Override
    public Optional<User> findUserByName(String name) {

        return userRepository.findUserByFullNameContaining(name);
    }

    @Override
    public UserView mapUser(User user) {

        UserView userView=new UserView();
        userView.setEmail(user.getEmail());
        userView.setRole(user.getRole().toString());
        userView.setUsername(user.getUsername());
        userView.setFullName(user.getFullName());
        userView.setProfilePicture(user.getProfilePicture());

        return userView;
    }

    @Override
    public UserView searchUser(UserSearchDto userSearchDto) {

        User user=null;

        switch (userSearchDto.getSearchType()) {
            case "Email":
                user=userRepository.findUserByEmail(userSearchDto.getValue()).orElse(null);
                break;
            case "Name":
                user=userRepository.findUserByFullNameContaining(userSearchDto.getValue()).orElse(null);
                break;
            case "Username":
                user=userRepository.findUserByUsername(userSearchDto.getValue()).orElse(null);
                break;
        }

        if (user!=null){

            return mapUser(user);
        }

        return null;
    }

    @Override
    public List<UserView> getAllUsers(String keyword) {

        if (keyword==null){

            return userRepository.findAll().stream().map(this::mapUser).collect(Collectors.toList());
        }

      return userRepository.findUserByFullNameContaining(keyword).stream().map(this::mapUser).collect(Collectors.toList());


    }


}
