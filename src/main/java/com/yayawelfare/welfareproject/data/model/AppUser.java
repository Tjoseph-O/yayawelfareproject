package com.yayawelfare.welfareproject.data.model;

import com.yayawelfare.welfareproject.data.model.enums.Gender;
import com.yayawelfare.welfareproject.data.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;
@Setter
@Getter
@Entity
@Table(name = "app_user")
public class AppUser {


    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phoneNumber;
    @Id
    @GeneratedValue(strategy = IDENTITY)

    private Long id;
    @CreatedDate

    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private Role role; // You should be Verified before you can be assigned a role for the program
    @Enumerated(EnumType.STRING)
    private Gender gender;


    private String profileImageUrl;
    private boolean isVerified; // You have to be present at the venue to be able to be verified

    @PrePersist
    public void setCreatedAt(){this.createdDate=LocalDateTime.now();}

}
