package com.example.Risto.exceptions;

import java.util.List;

import com.example.Risto.constants.Messages;

public class MissingIngredientException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MissingIngredientException(String message) {
		super(message);
	}
	
	public MissingIngredientException(List<String> missing) {
		String message = Messages.Errors.MISSING_INGREDIENT_EXCEPTION_MSG + String.join(", ", missing);
		new MissingIngredientException(message);
	}
}
