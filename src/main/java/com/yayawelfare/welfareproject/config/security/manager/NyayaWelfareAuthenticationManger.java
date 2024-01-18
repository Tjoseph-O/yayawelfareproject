package com.yayawelfare.welfareproject.config.security.manager;

import com.yayawelfare.welfareproject.exceptions.UserAuthenticationFailedException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class NyayaWelfareAuthenticationManger implements AuthenticationManager {

    private final AuthenticationProvider authenticationProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authenticationProvider.supports(authentication.getClass()))
            return authenticationProvider.authenticate(authentication);
        throw new UserAuthenticationFailedException("Unable to authenticate requests of this type");
    }

}
