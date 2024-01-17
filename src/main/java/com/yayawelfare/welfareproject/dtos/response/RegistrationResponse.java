package com.yayawelfare.welfareproject.dtos.response;

import com.yayawelfare.welfareproject.data.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class RegistrationResponse {
    private String message = "Welcome To NYAYA Welfare!!!";
    private Long id;
    private Role role;
}
