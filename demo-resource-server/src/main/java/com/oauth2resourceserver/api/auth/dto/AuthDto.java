package com.oauth2resourceserver.api.auth.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.oauth2resourceserver.api.auth.repository.User;

import lombok.Getter;
import lombok.Setter;

public class AuthDto implements UserDetails {
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private boolean isAccountNonExpired;
    @Getter @Setter private boolean isAccountNonLocked;
    @Getter @Setter private boolean isCredentialsNonExpired;
    @Getter @Setter private boolean isEnabled;
    @Getter @Setter private Collection<? extends GrantedAuthority> authorities;

    public AuthDto() {
    }
    
    public AuthDto(User users) {
        this.username = users.getUsername();
        this.password = users.getPassword();
        this.isAccountNonExpired = users.isAccountNonExpired();
        this.isAccountNonLocked = users.isAccountNonLocked();
        this.isCredentialsNonExpired = users.isCredentialsNonExpired();
        this.isEnabled = users.isEnabled();
    }

    public AuthDto(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}