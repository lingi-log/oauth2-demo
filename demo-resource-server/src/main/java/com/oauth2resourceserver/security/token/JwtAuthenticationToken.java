package com.oauth2resourceserver.security.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;
import lombok.Getter;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;
    @Getter final Object principal;
    @Getter private final Object username;
    @Getter private String credentials;

    /**
     * JwtAuthenticationToken
     * 
     * @param claims
     * 
     * 인증 전 토큰 생성.
     */
    public JwtAuthenticationToken(Claims claims) {
        super(null);
        super.setAuthenticated(false);

        this.principal = claims.get("username");
        this.username = claims.get("username");
        this.credentials = (String) claims.get("password");
    }

    /**
     * JwtAuthenticationToken
     * 
     * @param principal
     * @param credentials
     */
    public JwtAuthenticationToken(Object principal, String credentials) {
        super(null);
        super.setAuthenticated(false);

        this.principal = principal;
        this.username = principal;
        this.credentials = credentials;
    }

    /**
     * JwtAuthenticationToken
     * 
     * @param principal
     * @param credentials
     * @param authorities
     * 
     * 인증 후 토큰 생성
     */
    public JwtAuthenticationToken(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);

        this.principal = principal;
        this.username = principal;
        this.credentials = credentials;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        credentials = null;
    }

}



















































// import java.util.Collection;

// import com.example.demo.security.token.test.JwtTokenUtil;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AbstractAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.stereotype.Component;

// import io.jsonwebtoken.Claims;

// // import com.svlada.security.model.UserContext;
// // import com.svlada.security.model.token.RawAccessJwtToken;
// public class JwtAuthenticationToken extends AbstractAuthenticationToken {
//     private static final long serialVersionUID = 2877954820905567501L;
// 	private Object principal;
// 	private final Claims claims;
//     private final Object expDate;
// 	private final String jwtToken;
//     private Object credentials;

//     public JwtAuthenticationToken(String jwtToken) {
//         super(null);
//         JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//         super.setAuthenticated(true); // must use super, as we override
//         this.jwtToken=jwtToken;
//         this.claims = jwtTokenUtil.parseToken(jwtToken);

//         try{
//             if(claims == null){
//                 this.expDate = null;
//                 this.principal = null;
//                 this.credentials = null;               
//             }else{
//                 this.expDate = claims.get("exp");
//                 this.principal = claims.get("id");
//                 this.credentials = claims.get("password");
//             }
//         }catch(Exception e){
//             throw e;
//         }
//     }

//     @Override
//     public void setAuthenticated(boolean authenticated) {
//         if (authenticated) {
//             throw new IllegalArgumentException(
//                     "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
//         }
//         super.setAuthenticated(false);
//     }

//     @Override
//     public Object getCredentials() {
//         return "";
//     }

//     @Override
//     public Object getPrincipal() {
//         return this.principal;
//     }
// }