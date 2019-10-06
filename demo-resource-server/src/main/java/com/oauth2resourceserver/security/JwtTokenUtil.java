
package com.oauth2resourceserver.security;

import java.util.Date;

import com.oauth2resourceserver.security.token.JwtToken;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

@Component
public class JwtTokenUtil{
	@Setter private String salt = "test";
    @Setter private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public JwtTokenUtil(){}

    /**
     * 토큰 생성
     * expire time을 설정하고 id, password '비공개 claim'으로 등록
     * 
     * @param username
     * @param password
     * @return
     */
    public String create(String username, String password) throws Exception{
        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + 1000 * 60 * 60 * 24);

        String jwt = Jwts.builder()
						 .setHeaderParam("typ", "JWT")
                         .setExpiration(expireTime)
						 .claim("username", username)
                         .claim("password", password)
						 .signWith(signatureAlgorithm, this.generateKey())
                         .compact();

        return jwt;
    }

    /**
     * 
     * @param authentication
     * @return
     */
    public String create(Authentication authentication) throws Exception{
        return this.create((String) authentication.getPrincipal(), (String) authentication.getCredentials());
    }

    /**
     * string 형태로 전달 받은 token을 파싱한다.
     * token이 잘못되거나(파싱 할 수 없는 상태), expire time이 지난 경우 Exception을 던진다
     * 
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseToken(String jwt) throws Exception {
        try{
            Claims claims = Jwts.parser()
                                .setSigningKey(this.generateKey())
                                .parseClaimsJws(jwt)
                                .getBody();
            
            return claims;
        }catch(Exception e){
            throw e;
        }
    }
    
    /**
     * 
     * @param jwtToken
     * @return
     * @throws Exception
     */
    public Claims parseToken(JwtToken jwtToken) throws Exception{
        return parseToken(jwtToken.getToken());
    }

    /**
     * jwt 생성 시 필요한 key 생성
     * @return
     */
    private byte[] generateKey() throws Exception{
		byte[] key = null;
		try {
			key = salt.getBytes("UTF-8");
		} catch (Exception e) {
            throw e;
		}
		
		return key;
    }
}