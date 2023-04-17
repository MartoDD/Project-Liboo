package com.example.projectliboo.service.User;

import com.example.projectliboo.model.dtos.UserEditDto;
import com.example.projectliboo.model.dtos.UserRegisterDto;
import com.example.projectliboo.model.dtos.UserRoleChangeDto;
import com.example.projectliboo.model.entity.Book;
import com.example.projectliboo.model.entity.Role;
import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.model.view.UserView;
import com.example.projectliboo.repository.BookRepository;
import com.example.projectliboo.repository.RoleRepository;
import com.example.projectliboo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final BookRepository bookRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.bookRepository = bookRepository;
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

        UserView userView = new UserView();
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

        if (keyword == null) {

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

    @Override
    public void wantToRead(Long id, Principal principal) {

        Book book = bookRepository.findById(id).orElse(null);
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        if (!user.getBooks().contains(book)) {

            user.getBooks().add(book);
            user.getTbrBooks().add(book);

        } else {

            user.getCurrentlyReading().removeIf(b->b.getId()==book.getId());
            user.getReadBooks().removeIf(b->b.getId()==book.getId());
            user.getCurrentlyReading().removeIf(b->b.getId()==book.getId());
            if (!user.getTbrBooks().contains(book)) {
                user.getTbrBooks().add(book);
            }
        }

        userRepository.save(user);

    }

    @Override
    public void editUser(UserEditDto userEditDto, Principal principal) {

        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        user.setFullName(userEditDto.getFullName());
        user.setProfilePicture(userEditDto.getProfilePicture());
        user.setEmail(userEditDto.getEmail());
        userRepository.save(user);

    }

    @Override
    public void currentlyReading(Long id, Principal principal) {

        Book book = bookRepository.findById(id).orElse(null);
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        if (!user.getBooks().contains(book)) {

            user.getBooks().add(book);
            user.getCurrentlyReading().add(book);

        } else {

            user.getTbrBooks().removeIf(b->b.getId()==book.getId());
            user.getReadBooks().removeIf(b->b.getId()==book.getId());
            user.getCurrentlyReading().removeIf(b->b.getId()==book.getId());
            if (!user.getCurrentlyReading().contains(book)) {
                user.getCurrentlyReading().add(book);
            }
        }

        userRepository.save(user);


    }

    @Override
    public void read(Long id, Principal principal) {

        Book book = bookRepository.findById(id).orElse(null);
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        if (!user.getBooks().contains(book)) {

            user.getBooks().add(book);
            user.getReadBooks().add(book);

        } else {

            user.getCurrentlyReading().removeIf(b->b.getId()==book.getId());
            user.getTbrBooks().removeIf(b->b.getId()==book.getId());
            user.getCurrentlyReading().removeIf(b->b.getId()==book.getId());
            if (!user.getReadBooks().contains(book)) {
                user.getReadBooks().add(book);
            }
        }

        userRepository.save(user);


    }

    @Override
    public void remove(Long id, Principal principal) {

        Book book = bookRepository.findById(id).orElse(null);
        User user = userRepository.findUserByUsername(principal.getName()).orElse(null);
        user.getTbrBooks().removeIf(b->b.getId()==book.getId());
        user.getReadBooks().removeIf(b->b.getId()==book.getId());
        user.getCurrentlyReading().removeIf(b->b.getId()==book.getId());
        user.getBooks().removeIf(b->b.getId()==book.getId());
        userRepository.save(user);

    }


}
