package com.example.projectliboo.service;

import com.example.projectliboo.model.entity.User;
import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username).map(this::map).orElseThrow(() -> new UsernameNotFoundException("Username does not exist!"));
    }

    private UserDetails map(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                extractAuthorities(user)
        );
    }

    private List<GrantedAuthority> extractAuthorities(User user) {

        List<GrantedAuthority> list = new ArrayList<>();
        GrantedAuthority ga = mapRole(user.getRole());
        list.add(ga);
        return list;

    }

    private GrantedAuthority mapRole(RoleEnum role) {

        return new SimpleGrantedAuthority("ROLE_" + role.name());
    }
}
