package com.oauth2resourceserver.api.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.oauth2resourceserver.api.auth.dto.*;
import com.oauth2resourceserver.api.auth.repository.UserRepository;
import com.oauth2resourceserver.api.auth.repository.User;
import com.oauth2resourceserver.api.auth.repository.AuthorityRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

@Service("authService")
class AuthServiceImpl implements AuthService {
    @Autowired UserRepository userRepository;
    @Autowired AuthorityRepository authorityRepository;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        // Optional<User> user = userRepository.findById(id);
        // if(!user.isPresent()) throw new UsernameNotFoundException(id);
        // AuthDto authDto = new AuthDto(user.get());
        // authDto.setAuthorities(getAuthorities(id));

        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id));
        AuthDto authDto = new AuthDto(user);
        authDto.setAuthorities(getAuthorities(id));

        return authDto;
    }

    public Collection<GrantedAuthority> getAuthorities(String id) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<String> strAuthorities = authorityRepository.findAllById(id);

        for(String authority : strAuthorities){
            authorities.add(new SimpleGrantedAuthority(authority));
        }

        return authorities;
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return this.passwordEncoder;
    }

    @Override
    public boolean signUp(AuthDto authDto) {
        userRepository.save(
            User.builder()
            .username(authDto.getUsername())
            .password(new BCryptPasswordEncoder().encode(authDto.getPassword()))
            .build());
        
        return true;
    }
}