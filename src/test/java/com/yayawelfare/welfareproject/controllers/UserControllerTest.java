package com.yayawelfare.welfareproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yayawelfare.welfareproject.dtos.request.RegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testRegister(){
        ObjectMapper mapper = new ObjectMapper();
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("tejonic399@qianhost.com");
        request.setPassword("password");
        try {
            mockMvc.perform(post("/api/v1/user")
                            .content(mapper.writeValueAsString(request))
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Test
    public void testRegisterMe(){
        ObjectMapper mapper = new ObjectMapper();
        RegistrationRequest request = new RegistrationRequest();
        request.setPhoneNumber("0813772921000");
        request.setEmail("testme@gmail.com");
        request.setPassword("password");
        try {
            mockMvc.perform(post("/api/v1/user")
                            .content(mapper.writeValueAsString(request))
                            .contentType(APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


    @Test
//    @Sql("/db/insert.sql")
    @WithUserDetails(value = "0813772921000")
    public void testGetUserById(){
        String token ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE2OTkzNzgwMDIsImV4cCI6MTY5OTQ2NDQwMiwiaXNzIjoiR2VtcyB0dWJlIGluYy4iLCJzdWIiOiJqb2huQGVtYWlsLmNvbSJ9.aZvMaf5lXAgWbkMcbhUPfbwvlzAa4c3tRvg4bYiJKoM";
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/43")
                            .header("Authorization", token      ))
                    .andExpect(status().is2xxSuccessful())
                    .andDo(print());
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }

    }

}
