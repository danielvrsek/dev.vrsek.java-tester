package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.EvaluationContext;
import dev.vrsek.javatester.modules.EvaluationError;
import dev.vrsek.javatester.modules.IReadOnlyEvaluationContext;

public class FieldTypeEvaluator implements IFieldEvaluator {
	private final Class type;
	private final String expectedType;

	public FieldTypeEvaluator(Class type, String expectedType) {

		this.type = type;
		this.expectedType = expectedType;
	}

	@Override
	public Boolean evaluate(IReadOnlyEvaluationContext readOnlyContext) {
		EvaluationContext context = new EvaluationContext("fieldTypeEvaluator", readOnlyContext);

		if (!type.getTypeName().equals(expectedType)) {
			context.addEvaluationError(
					new EvaluationError("Field has invalid type. Used type: '%s', expected: '%s'", type.getTypeName(), expectedType)
			);

			return false;
		}

		return true;
	}
}
