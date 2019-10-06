package com.oauth2resourceserver.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author jangho.han
 * 
 * June 11, 2019
 */

public class InvalidTokenException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public InvalidTokenException(String message){
        super(message);
    }

    public InvalidTokenException(String message, Throwable t) {
		super(message, t);
	}
}