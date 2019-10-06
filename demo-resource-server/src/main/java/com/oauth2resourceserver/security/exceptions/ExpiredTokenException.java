package com.oauth2resourceserver.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

public class ExpiredTokenException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public ExpiredTokenException(String message){
        super(message);
    }

    public ExpiredTokenException(String message, Throwable t) {
		super(message, t);
	}
}