package com.yayawelfare.welfareproject.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
    private String phoneNumber;
    private String password;
}
