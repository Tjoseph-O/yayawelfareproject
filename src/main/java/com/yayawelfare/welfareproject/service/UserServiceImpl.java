package com.yayawelfare.welfareproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.data.repository.UserRepository;
import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.RegistrationResponse;
import com.yayawelfare.welfareproject.dtos.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper mapper;








    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest){
        AppUser user = new AppUser();
        user.setFirstName(registrationRequest.getFirstName());
        user.setMiddleName(registrationRequest.getMiddleName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setAddress(registrationRequest.getAddress());
        user.setPassword(registrationRequest.getPassword());
        AppUser savedUser = userRepository.save(user);
        return new RegistrationResponse(savedUser.getId());
    }

    @Override
    public UserResponse login(LoginRequest request){
        AppUser user = userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(),request.getPassword())
                .orElseThrow(() -> new RuntimeException());
        return UserResponse.builder()
//                .id(user.getId())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
//                .createdDate(user.getCreatedDate())
                .build();

    }

    public UserResponse getUserDetails(String userEmail){
        AppUser foundUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Could not find"));

        return UserResponse.of(foundUser);
    }





}
