package com.yayawelfare.welfareproject.config;

import com.yayawelfare.welfareproject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

//    public ModelMapper mapper(){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setSkipNullEnabled(true);
//        mapper.getConfiguration().setAmbiguityIgnored(true);
//        return mapper;
//    }


    @Bean
    public UserDetailsService userDetailsService(UserService userService){
        return (username)-> getUserByUsername(userService, username);
    }

    private static User getUserByUsername(UserService userService, String phoneNumber) {
        var user = userService.getUserBy(phoneNumber);
        var authorities = user.getAuthorities();
        var userAuthorities =
                authorities.stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.name()))
                        .toList();
        return new User(phoneNumber, user.getPassword(), userAuthorities);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}
}
