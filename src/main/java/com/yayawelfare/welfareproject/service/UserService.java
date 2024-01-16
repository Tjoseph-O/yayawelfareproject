package com.yayawelfare.welfareproject.service;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegisterRequest;
import com.yayawelfare.welfareproject.dtos.response.UserInfoResponse;

public interface UserService {

   void register(RegisterRequest request);


    UserInfoResponse login(LoginRequest request);
}
