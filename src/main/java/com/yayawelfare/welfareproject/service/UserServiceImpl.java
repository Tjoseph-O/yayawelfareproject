package com.yayawelfare.welfareproject.service;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.data.repository.UserRepository;
import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegisterRequest;
import com.yayawelfare.welfareproject.dtos.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public void register(RegisterRequest request){
        AppUser user = new AppUser();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

    @Override
    public UserInfoResponse login(LoginRequest request){
        AppUser user = userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(),request.getPassword())
                .orElseThrow(() -> new RuntimeException());
        return UserInfoResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .createdDate(user.getCreatedDate())
                .build();




    }




}
