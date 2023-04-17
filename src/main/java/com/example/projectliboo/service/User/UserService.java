package com.example.projectliboo.service.User;

import com.example.projectliboo.model.dtos.UserEditDto;
import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.model.dtos.UserRoleChangeDto;
import com.example.projectliboo.model.dtos.UserSearchDto;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.view.UserView;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDto userRegisterDto);
    Optional<User> findUserByEmail(String email);
    Optional<User>findUserByUsername(String username);
    List<User>findUserByName(String name);
    UserView mapUser(User user);

    List<UserView>getAllUsers(String keyword);
    void deleteUser(Long id);
    void changeUserRole(UserRoleChangeDto userRoleChangeDto,Long id);

    void wantToRead(Long id, Principal principal);

    void editUser(UserEditDto userEditDto, Principal principal);
    void currentlyReading(Long id,Principal principal);
    void read(Long id,Principal principal);
    void remove(Long id,Principal principal);

}
