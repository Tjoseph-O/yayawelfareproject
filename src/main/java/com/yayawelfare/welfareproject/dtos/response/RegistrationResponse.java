package com.yayawelfare.welfareproject.dtos.response;

import lombok.*;


@Data
//@Builder
@AllArgsConstructor
@RequiredArgsConstructor

public class RegistrationResponse {
    private String message = "Welcome To NYAYA Welfare!!!";
    private Long id;
//    private Role role;
    private String lastName;
//    private String email;



}
