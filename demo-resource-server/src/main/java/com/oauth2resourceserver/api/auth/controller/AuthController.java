package com.oauth2resourceserver.api.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.HashMap;

import com.oauth2resourceserver.base.constants.Constants;
import com.oauth2resourceserver.base.response.ResponseBodyDTO;
import com.oauth2resourceserver.security.JwtTokenUtil;
import com.oauth2resourceserver.security.token.JwtAuthenticationToken;
import com.oauth2resourceserver.api.auth.dto.AuthDto;
import com.oauth2resourceserver.api.auth.service.AuthService;

import org.springframework.security.core.userdetails.UserDetails;

@RestController
@EnableAutoConfiguration
@RequestMapping("/api/auth")
public class AuthController {

    @Resource(name="authService")
    private AuthService authService;
    private JwtTokenUtil jwtTokenUtil;
    private ResponseBodyDTO<Object> responseBodyDTO;

    @Autowired
    public AuthController(ResponseBodyDTO<Object> responseBodyDTO,
                          JwtTokenUtil jwtTokenUtil){

        this.responseBodyDTO = responseBodyDTO;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value="/sign-in")
    public ResponseEntity<ResponseBodyDTO<Object>> signIn(@RequestBody AuthDto authDto, HttpSession session, HttpServletResponse response) throws Exception{
        UserDetails userDetails = authService.loadUserByUsername(authDto.getUsername());
        response.setHeader("Authorization", jwtTokenUtil.create(userDetails.getUsername(), authDto.getPassword()));
        response.setHeader("Access-Control-Expose-Headers", "*");
        
        responseBodyDTO.setData(new JwtAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities()));

        return new ResponseEntity<ResponseBodyDTO<Object>>(responseBodyDTO, HttpStatus.OK);
    }
    
    @PostMapping(value="/sign-up")
    public void signUp(@RequestBody AuthDto authDto){
        authService.signUp(authDto);
    }
    

    @GetMapping(value="/sign-out")
    public Map<String, Object> signOut() throws Exception{
        Map<String, Object> rtnMap = new HashMap<>();
        rtnMap.put("rtnMsg", Constants.SUCCESS);

        try{
            Map<String, Object> dataMap = new HashMap<>();
        }catch(Exception e){
            rtnMap.put("rtnMsg", Constants.ERROR);
        }

        return rtnMap;
    }
}