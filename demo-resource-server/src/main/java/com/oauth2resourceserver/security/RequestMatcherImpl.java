package com.oauth2resourceserver.security;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

public class RequestMatcherImpl implements RequestMatcher {
    private OrRequestMatcher orRequestMatcher;
    private RequestMatcher requestMatcher;

    public RequestMatcherImpl(List<String> skipPath, String path){
        List<RequestMatcher> m = skipPath.stream().map(tmpPath -> new AntPathRequestMatcher(tmpPath)).collect(Collectors.toList());
        orRequestMatcher = new OrRequestMatcher(m);
        requestMatcher = new AntPathRequestMatcher(path);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (orRequestMatcher.matches(request)) {
            return false;
        }
        return requestMatcher.matches(request) ? true : false;
    }
    
}