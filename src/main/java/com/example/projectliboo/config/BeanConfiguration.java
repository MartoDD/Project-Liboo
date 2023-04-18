package com.example.projectliboo.config;

import com.example.projectliboo.model.enums.RoleEnum;
import com.example.projectliboo.model.view.UserView;
import com.example.projectliboo.repository.UserRepository;
import com.example.projectliboo.service.AppUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BeanConfiguration {

    private final UserRepository userRepository;

    public BeanConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/", "/user/login", "/user/register", "/user/login-error", "/css/**", "/img/**", "/resources/**", "/static/**", "/templates/**").permitAll()
                .requestMatchers("/librarian/*").hasRole(RoleEnum.LIBRARIAN.name())
                .requestMatchers("/admin/*").hasRole(RoleEnum.ADMIN.name())
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/user/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/")
                .failureForwardUrl("/user/login-error")
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .configure(httpSecurity.requestCache().disable());

        httpSecurity.exceptionHandling().accessDeniedPage("/403.html");


        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AppUserDetailsService(userRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserView userView() {
        return new UserView();
    }
}
