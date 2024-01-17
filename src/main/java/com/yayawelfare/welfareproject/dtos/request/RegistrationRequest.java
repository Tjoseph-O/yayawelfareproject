package com.yayawelfare.welfareproject.dtos.request;

import com.yayawelfare.welfareproject.data.model.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegistrationRequest {

    private String firstName;
    private String middleName;
    private String lastName;
    private String address;

    private String password;

    private String email;

    private String phoneNumber;
    private Role role;


}
