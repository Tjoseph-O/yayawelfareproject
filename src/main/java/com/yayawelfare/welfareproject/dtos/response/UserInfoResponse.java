package com.yayawelfare.welfareproject.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserInfoResponse {
    private String firstName;
    private String middleName;
    private String lastName;
    private long id;
    private LocalDateTime createdDate;
}
