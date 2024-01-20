package com.yayawelfare.welfareproject.services;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.RegistrationResponse;
import com.yayawelfare.welfareproject.dtos.response.UserResponse;
import com.yayawelfare.welfareproject.exceptions.NyayaWelfareException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private RegistrationRequest registerRequest;
    private RegistrationResponse registerResponse;

    @BeforeEach

    public void setUp(){
        registerRequest = new RegistrationRequest();
        registerRequest.setPhoneNumber("08137729210");
        registerRequest.setEmail("wocog82484@mugadget.com");
        registerRequest.setPassword("password");
        registerResponse = userService.register(registerRequest);
    }

    @Test
    public void registerTest(){
        assertNotNull(registerResponse);
        assertNotNull(registerResponse.getId());
    }

    @Test
    public void testGetUserById() throws NyayaWelfareException{
        AppUser appUser = userService.getUserById(registerResponse.getId());
        assertThat(appUser).isNotNull();
    }

    @Test
//    @Sql("/db/insert.sql")
    public void getUsers(){
        List<UserResponse> users = userService.getUsers(1, 3);
        log.info("users:: {}", users);
        assertThat(users).hasSize(3);

    }

}
