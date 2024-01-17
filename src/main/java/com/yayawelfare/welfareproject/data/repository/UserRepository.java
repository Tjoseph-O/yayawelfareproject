package com.yayawelfare.welfareproject.data.repository;

import com.yayawelfare.welfareproject.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser>findByPhoneNumberAndPassword(String phoneNumber, String password);


    Optional<AppUser> findByEmail(String userEmail);
}
