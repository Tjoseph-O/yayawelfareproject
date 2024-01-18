package com.yayawelfare.welfareproject.config.security;

import com.yayawelfare.welfareproject.config.security.filters.NyayaWelfareAuthorizationFilter;
import com.yayawelfare.welfareproject.config.security.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final NyayaWelfareAuthorizationFilter nyayaWelfareAuthorizationFilter;
}
