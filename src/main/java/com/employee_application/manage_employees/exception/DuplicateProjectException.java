package com.employee_application.manage_employees.exception;

public class DuplicateProjectException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateProjectException(String message) {
		super(message);
	}
}
