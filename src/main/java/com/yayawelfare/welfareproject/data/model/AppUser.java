package com.yayawelfare.welfareproject.data.model;

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

//    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
//    @Column(name = "middle_name", length = 50)
    private String middleName;
//    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
//    @Column(name = "address", nullable = false, length = 225)
    private String address;
    private String password;
    private String email;
    private String phoneNumber;
    @Id
    @GeneratedValue(strategy = IDENTITY)
//    @TableGenerator(name = "app_user_id_generator", allocationSize = 1)
    private Long id;
    @CreatedDate
//    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private Role role;

    @PrePersist
    public void setCreatedAt(){this.createdDate=LocalDateTime.now();}

}
