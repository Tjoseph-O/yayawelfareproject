package com.yayawelfare.welfareproject.dtos.response;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.data.model.enums.Role;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
public class UserResponse {
    private String firstName;
    private String middleName;
    private String lastName;



    public static UserResponse of(AppUser appUser) {
        return new UserResponse(appUser.getFirstName(), appUser.getMiddleName(), appUser.getLastName());
    }
}
