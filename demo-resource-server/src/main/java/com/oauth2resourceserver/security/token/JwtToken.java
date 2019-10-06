package com.oauth2resourceserver.security.token;

import lombok.Data;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */
@Data
public class JwtToken{
    private final String token;
    private String username;
    private String password;

    public JwtToken(String token){
        this.token = token;
    }

    public String toString(){
        return token;
    }
}