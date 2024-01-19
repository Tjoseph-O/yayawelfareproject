package com.yayawelfare.welfareproject.services;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.data.repository.UserRepository;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.RegistrationResponse;
import com.yayawelfare.welfareproject.dtos.response.UserResponse;
import com.yayawelfare.welfareproject.exceptions.NyayaWelfareException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;


import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper mapper;








    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest){
        AppUser user = new AppUser();
//        user.setFirstName(registrationRequest.getFirstName());
//        user.setMiddleName(registrationRequest.getMiddleName());
//        user.setSurname(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
//        user.setAddress(registrationRequest.getAddress());
        user.setPassword(registrationRequest.getPassword());
        AppUser savedUser = userRepository.save(user);
        return new RegistrationResponse(savedUser.getId());
//        return mapper.map(user, RegistrationResponse.class);
    }


    @Override
    public List<UserResponse> getUsers(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<AppUser> appUserPage = userRepository.findAll(pageable);
        List<AppUser> appUsers = appUserPage.getContent();
        return appUsers.stream()
                        .map(user->mapper.map(user, UserResponse.class))
                        .toList();
    }

    @Override
    public UserResponse getUserBy(Long id) throws NyayaWelfareException{
        AppUser appUser = getUserById(id);
        return mapper.map(appUser, UserResponse.class);
    }

    @Override
    public AppUser getUserById(Long id) throws NyayaWelfareException {
        return userRepository.findById(id).orElseThrow(
                ()-> new NyayaWelfareException(String.format("User with id %d is not Found", id))
        );

    }

//    @Override
//    public UserResponse login(LoginRequest request){
//        AppUser user = userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(),request.getPassword())
//                .orElseThrow(() -> new RuntimeException());
//        return UserResponse.builder()
////                .id(user.getId())
//                .firstName(user.getFirstName())
//                .middleName(user.getMiddleName())
//                .lastName(user.getLastName())
////                .createdDate(user.getCreatedDate())
//                .build();
//
//    }

//    public UserResponse getUserDetails(String userEmail){
//        AppUser foundUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("Could not find"));
//
//        return UserResponse.of(foundUser);
//    }


    @Override
    public AppUser getUserBy(String phoneNumber)  {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(()->
                new RuntimeException(
                        String.format("user with Phone Number %s not found", phoneNumber)
                ));
    }


}
