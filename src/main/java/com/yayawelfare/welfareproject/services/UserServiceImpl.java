package com.yayawelfare.welfareproject.services;

import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.data.repository.UserRepository;
import com.yayawelfare.welfareproject.dtos.request.LoginRequest;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import com.yayawelfare.welfareproject.dtos.response.LoginResponse;
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

import static com.yayawelfare.welfareproject.data.model.enums.Authority.USER;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper mapper;








    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest){
        AppUser user = new AppUser();
        user.setEmail(registrationRequest.getEmail());
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setPassword(registrationRequest.getPassword());
        user.setAuthorities(List.of(USER));
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

    @Override
    public LoginResponse login(LoginRequest request){
        AppUser user = userRepository.findByPhoneNumberAndPassword(request.getPhoneNumber(),request.getPassword())
                .orElseThrow(() -> new RuntimeException());
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getPhoneNumber());

        return new LoginResponse(user.getPassword());

    }




    @Override
    public AppUser getUserBy(String email)  {
        return userRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(
                        String.format("user with Phone Number %s not found", email)
                ));
    }


}
