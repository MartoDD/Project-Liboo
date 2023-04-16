package com.example.projectliboo.service.User;

import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.model.dtos.UserRoleChangeDto;
import com.example.projectliboo.model.dtos.UserSearchDto;
import com.example.projectliboo.model.entity.Role;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.model.view.UserView;
import com.example.projectliboo.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        User user = new User();
        Role role = roleRepository.findByRoleName(RoleEnum.USER);
        user.setEmail(userRegisterDto.getEmail());
        user.setFullName(userRegisterDto.getFullName());
        user.setUsername(userRegisterDto.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        user.setRole(role);
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
    public List<User> findUserByName(String name) {

        return userRepository.findUserByFullNameContaining(name);
    }

    @Override
    public UserView mapUser(User user) {

        UserView userView=new UserView();
        userView.setId(user.getId());
        userView.setEmail(user.getEmail());
        userView.setRole(user.getRole().getRoleName().toString());
        userView.setUsername(user.getUsername());
        userView.setFullName(user.getFullName());
        userView.setProfilePicture(user.getProfilePicture());

        return userView;
    }


    @Override
    public List<UserView> getAllUsers(String keyword) {

        if (keyword==null){

            return userRepository.findAll().stream().map(this::mapUser).collect(Collectors.toList());
        }

      return userRepository.findUserByFullNameContaining(keyword).stream().map(this::mapUser).collect(Collectors.toList());


    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);

    }

    @Override
    public void changeUserRole(UserRoleChangeDto userRoleChangeDto, Long id) {

        User user = userRepository.findById(id).orElse(null);
        Role role = roleRepository.findByRoleName(RoleEnum.valueOf(userRoleChangeDto.getRole()));
        user.setRole(role);
        userRepository.save(user);

    }


}
