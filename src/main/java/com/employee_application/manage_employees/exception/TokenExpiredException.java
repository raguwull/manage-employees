package com.employee_application.manage_employees.exception;

public class TokenExpiredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public TokenExpiredException(String message) {
		super(message);
	}
}