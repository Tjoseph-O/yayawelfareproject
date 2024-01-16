package com.yayawelfare.welfareproject.controller;


import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegisterRequest;
import com.yayawelfare.welfareproject.dtos.response.UserInfoResponse;
import com.yayawelfare.welfareproject.service.UserService;
import com.yayawelfare.welfareproject.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        userService.register(request);
        return new ResponseEntity<>("Welcome To NYAYA Welfare Platform!!!", HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserInfoResponse> login(@RequestBody LoginRequest request){
        var response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
