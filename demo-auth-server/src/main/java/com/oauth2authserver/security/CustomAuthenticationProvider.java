package com.oauth2authserver.security;

import com.oauth2authserver.entity.ResourceOwner;
import com.oauth2authserver.repository.ResourceOwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private ResourceOwnerRepository resourceOwnerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("CustomAuthenticationProvider.authenticae :::: {}", authentication);
//        log.info("UserDetailsServiceImpl.loadUserByUsername :::: {}",username);
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        ResourceOwner resourceOwner = resourceOwnerRepository.findByUsername(userName);
        if(resourceOwner == null){
            throw new UsernameNotFoundException("User name not found!");
        }

        if(!passwordEncoder.matches(password, resourceOwner.getPassword())){
            throw  new BadCredentialsException("Password is not valid");
        }
        return new UsernamePasswordAuthenticationToken(userName, password, resourceOwner.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
