package com.yayawelfare.welfareproject.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor

@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String firstName;
    private String id;
    private String phoneNumber;
//    private String profileImage;



//    public static UserResponse of(AppUser appUser) {
//        return new UserResponse(appUser.getFirstName(), appUser.getMiddleName(), appUser.getLastName());
//    }
}
