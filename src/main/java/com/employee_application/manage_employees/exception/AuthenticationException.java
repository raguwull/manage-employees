package com.employee_application.manage_employees.exception;

public class AuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message) {
		super(message);
	}
}
