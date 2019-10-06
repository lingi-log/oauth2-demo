package com.oauth2resourceserver.security.provider;

import com.oauth2resourceserver.security.token.JwtAuthenticationToken;
import com.oauth2resourceserver.security.token.JwtToken;


import com.oauth2resourceserver.api.auth.repository.AuthorityRepository;
import com.oauth2resourceserver.security.JwtTokenUtil;
import com.oauth2resourceserver.security.exceptions.ExpiredTokenException;
import com.oauth2resourceserver.security.exceptions.InvalidTokenException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    // private JwtTokenUtil jwtTokenUtil;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    @Autowired private AuthorityRepository authorityRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // jwtTokenUtil = new JwtTokenUtil();
        JwtToken jwtToken = (JwtToken) authentication.getPrincipal();
        Claims claims = null;
        try{
            claims = jwtTokenUtil.parseToken(jwtToken);
            jwtToken.setUsername((String)claims.get("username"));
            jwtToken.setPassword((String)claims.get("password"));
        }catch(ExpiredJwtException e){
            throw new ExpiredTokenException(e.getMessage());
        }catch(Exception e){
            throw new InvalidTokenException(e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        //exp date check
        return authentication;
    }

    // public List<GrantedAuthority> getAuthorities(String id) {
    //     List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    //     List<String> strAuthorities = authorityRepository.findAllById(id);

    //     for(String authority : strAuthorities){
    //         authorities.add(new SimpleGrantedAuthority(authority));
    //     }

    //     return authorities;
    // }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}