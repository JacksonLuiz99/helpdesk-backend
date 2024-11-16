package com.jackson.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class validationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();

	public validationError() {
		super();
	}

	public validationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
	
}
