package com.oauth2resourceserver.api.auth.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name="user")
@Entity
public class User{
    @Id
    private String username;

    @Column(length=100, nullable=false)
    private String password;
    
    @Column(name="IS_ACCOUNT_NON_EXPIRED")
    private boolean isAccountNonExpired;
    @Column(name="IS_ACCOUNT_NON_LOCKED")
    private boolean isAccountNonLocked;
    @Column(name="IS_CREDENTIALS_NON_EXPIRED")
    private boolean isCredentialsNonExpired;
    @Column(name="IS_ENABLED")
    private boolean isEnabled;

    @Builder
    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }
}