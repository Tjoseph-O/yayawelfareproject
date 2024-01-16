package com.yayawelfare.welfareproject.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phoneNumber;


}
