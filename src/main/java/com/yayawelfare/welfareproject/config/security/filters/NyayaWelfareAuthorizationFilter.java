package com.yayawelfare.welfareproject.config.security.filters;

import com.yayawelfare.welfareproject.config.security.service.JwtService;
import com.yayawelfare.welfareproject.config.security.utils.SecurityUtils;
import com.yayawelfare.welfareproject.data.model.AppUser;
import com.yayawelfare.welfareproject.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@Slf4j
@AllArgsConstructor
public class NyayaWelfareAuthorizationFilter extends OncePerRequestFilter {


    private final JwtService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        boolean isRequestToPublicEndpoint =request.getMethod().equals("POST")&&
                SecurityUtils.getPublicEndpoints().contains(request.getServletPath());

        if (isRequestToPublicEndpoint) filterChain.doFilter(request, response);
        else {

            String authorizationHeader = request.getHeader("Authorization");
            log.info("auth header:: {}", authorizationHeader);
            String token = authorizationHeader.substring("Bearer ".length());
            String username = jwtService.extractUsernameFrom(token);

            log.info("username:: {}", username);
            AppUser appUser = userService.getUserBy(username);
            var authorities = appUser.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.name()))
                    .toList();
            log.info("authorities:: {}", authorities);
            var authentication = new UsernamePasswordAuthenticationToken(appUser.getEmail(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        }
    }


}
