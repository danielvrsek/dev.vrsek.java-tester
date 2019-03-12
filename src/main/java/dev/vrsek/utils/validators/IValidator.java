package dev.vrsek.utils.validators;

import dev.vrsek.utils.exceptions.ValidationException;

public interface IValidator {
	void validate() throws ValidationException;
}
