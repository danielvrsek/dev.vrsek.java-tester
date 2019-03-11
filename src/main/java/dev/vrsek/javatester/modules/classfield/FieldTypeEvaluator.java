package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.EvaluationError;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FieldTypeEvaluator implements IFieldEvaluator {
	private final Class type;
	private final String expectedType;

	public FieldTypeEvaluator(Class type, String expectedType) {

		this.type = type;
		this.expectedType = expectedType;
	}

	@Override
	public Collection<EvaluationError> evaluate() {
		List<EvaluationError> errors = new ArrayList<>();

		if (!type.getTypeName().equals(expectedType)) {
			errors.add(
					new EvaluationError("Field has invalid type. Used type: '%s', expected: '%s'", type.getTypeName(), expectedType)
			);
		}

		return errors;
	}
}
