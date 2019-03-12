package dev.vrsek.utils.validators;

import dev.vrsek.utils.exceptions.ValidationException;

import java.util.function.Supplier;

public class NotNullOrEmptyStringValidator extends DynamicValidator<String> {
	public static final String DEFAULT_MESSAGE = "String cannot be null or empty.";

	public NotNullOrEmptyStringValidator(Supplier<String> supplier) {
		this(supplier, DEFAULT_MESSAGE);
	}

	public NotNullOrEmptyStringValidator(Supplier<String> supplier, String errorMessage) {
		super(supplier, errorMessage);
	}

	@Override
	public void validate() throws ValidationException {
		String value = getValue();

		if (value == null || value.isEmpty()) {
			throw new ValidationException(getErrorMessage());
		}
	}
}
