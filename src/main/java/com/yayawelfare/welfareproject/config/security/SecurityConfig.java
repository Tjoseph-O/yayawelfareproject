package com.yayawelfare.welfareproject.config.security;

import com.yayawelfare.welfareproject.config.security.filters.NyayaAuthenticationFilter;
import com.yayawelfare.welfareproject.config.security.filters.NyayaWelfareAuthorizationFilter;
import com.yayawelfare.welfareproject.config.security.service.JwtService;
import com.yayawelfare.welfareproject.config.security.utils.SecurityUtils;
import com.yayawelfare.welfareproject.data.model.enums.Authority;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final NyayaWelfareAuthorizationFilter authorizationFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(c->c.disable())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedMethods(List.of("POST", "PUT", "GET"));
                    corsConfiguration.setAllowedOrigins(List.of("*"));
                })
                .addFilterAt(new NyayaAuthenticationFilter(authenticationManager, jwtService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(authorizationFilter, NyayaAuthenticationFilter.class)
                .authorizeHttpRequests(c->c.requestMatchers(HttpMethod.POST, getPublicEndpoints()).permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/user", "/api/v1/user/**").hasAnyAuthority(Authority.USER.name()))
                .build();
    }
    /**
     * get user by id ==> /api/v1/user/1
     * /api/v1/user != /api/v1/user/1
     * get all users ==> /api/v1/user?page=1&size=5 ==  /api/v1/user
     * */

    private static String[] getPublicEndpoints(){
        return SecurityUtils.getPublicEndpoints()
                .toArray(String[]::new);
    }



}
