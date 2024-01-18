package com.yayawelfare.welfareproject.dtos.response;

import com.yayawelfare.welfareproject.data.model.enums.Role;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationResponse {
    private String message = "Welcome To NYAYA Welfare!!!";
    private Long id;
    private Role role;
    private String lastName;
    private String email;



}
