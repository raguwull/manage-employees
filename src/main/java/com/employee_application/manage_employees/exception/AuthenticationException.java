package com.employee_application.manage_employees.exception;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message) {
		super(message);
	}
}
