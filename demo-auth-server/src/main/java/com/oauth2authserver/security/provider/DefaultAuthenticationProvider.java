package com.oauth2authserver.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class DefaultAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication.getName() == null || authentication.getCredentials() == null){
            return null;
        }

        final String userName = authentication.getName();
        final Object userPassword = authentication.getCredentials();

        if(userName.isEmpty() || userPassword.toString().isEmpty()){
            return null;
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
