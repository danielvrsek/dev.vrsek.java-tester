package dev.vrsek.utils.validators;

import dev.vrsek.utils.exceptions.ValidationException;

import java.util.function.Supplier;

public class NotNullObjectValidator extends DynamicValidator {
	public static final String DEFAULT_MESSAGE = "Object cannot be null.";

	public NotNullObjectValidator(Supplier supplier) {
		this(supplier, DEFAULT_MESSAGE);
	}

	public NotNullObjectValidator(Supplier supplier, String errorMessage) {
		super(supplier, errorMessage);
	}

	@Override
	public void validate() throws ValidationException {
		if (getValue() == null) {
			throwValidationException();
		}
	}
}
