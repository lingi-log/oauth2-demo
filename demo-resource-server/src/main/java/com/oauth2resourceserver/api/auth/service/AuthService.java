package com.oauth2resourceserver.api.auth.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

import com.oauth2resourceserver.api.auth.dto.AuthDto;

public interface AuthService extends UserDetailsService {
    public Collection<GrantedAuthority> getAuthorities(String id);
    public PasswordEncoder passwordEncoder();
    public boolean signUp(AuthDto authDto);
}