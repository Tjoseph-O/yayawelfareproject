package com.yayawelfare.welfareproject.services;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.RegistrationResponse;
import com.yayawelfare.welfareproject.dtos.response.UserResponse;
import com.yayawelfare.welfareproject.exceptions.NyayaWelfareException;

import java.util.List;

public interface UserService {

   RegistrationResponse register(RegistrationRequest registrationRequest);


    List<UserResponse> getUsers(int page, int size);

    UserResponse getUserBy(Long id) throws NyayaWelfareException;

    AppUser getUserById(Long id) throws NyayaWelfareException;

    AppUser getUserBy(String phoneNumber);

//    UserResponse login(LoginRequest request);
}
