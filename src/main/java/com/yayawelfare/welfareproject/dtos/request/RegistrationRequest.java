package com.yayawelfare.welfareproject.dtos.request;

import com.yayawelfare.welfareproject.data.model.enums.Role;
import jakarta.validation.constraints.Email;
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
    @Email(message = "invalid email address",regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
    private String email;

    private String phoneNumber;
    private Role role;


}
