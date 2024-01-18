package com.yayawelfare.welfareproject.controller;


import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.RegistrationResponse;
import com.yayawelfare.welfareproject.dtos.response.UserResponse;
import com.yayawelfare.welfareproject.exceptions.NyayaWelfareException;
import com.yayawelfare.welfareproject.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest){
       return ResponseEntity.status(CREATED).body(userService.register(registrationRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){
        var response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getUserBy(id));
        } catch (NyayaWelfareException exception){
            return ResponseEntity.badRequest().body(exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(userService.getUsers(page, size));
    }

}
