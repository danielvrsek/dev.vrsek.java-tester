package dev.vrsek.utils.validators;

import dev.vrsek.utils.exceptions.ValidationException;

public abstract class ValidatorBase implements IValidator {
	private final String errorMessage;

	protected ValidatorBase(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	protected void throwValidationException() throws ValidationException {
		throw new ValidationException(errorMessage);
	}

	protected  String getErrorMessage() {
		return errorMessage;
	}
}
