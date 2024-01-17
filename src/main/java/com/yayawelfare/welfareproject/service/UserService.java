package com.yayawelfare.welfareproject.service;

import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.RegistrationResponse;
import com.yayawelfare.welfareproject.dtos.response.UserResponse;

public interface UserService {

   RegistrationResponse register(RegistrationRequest registrationRequest, String origin);




    UserResponse login(LoginRequest request);
}
