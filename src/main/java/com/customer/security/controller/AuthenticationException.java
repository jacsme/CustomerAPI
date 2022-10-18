package com.customer.security.controller;

public class AuthenticationException extends RuntimeException {
	private static final long serialVersionUID = -6371784122851090022L;
	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
