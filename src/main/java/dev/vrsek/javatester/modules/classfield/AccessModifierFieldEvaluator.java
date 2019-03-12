package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.EvaluationContext;
import dev.vrsek.javatester.modules.EvaluationError;
import dev.vrsek.javatester.modules.IReadOnlyEvaluationContext;

import java.lang.reflect.Modifier;

public class AccessModifierFieldEvaluator implements IFieldEvaluator {

	private final Integer modifiers;
	private final Integer expectedModifier;

	public AccessModifierFieldEvaluator(Integer modifiers, Integer expectedModifier){
		this.modifiers = modifiers;
		this.expectedModifier = expectedModifier;
	}

	@Override
	public Boolean evaluate(IReadOnlyEvaluationContext readOnlyContext) {
		EvaluationContext context = new EvaluationContext("accessModifierEvaluator", readOnlyContext);
		if ((expectedModifier & modifiers) != expectedModifier) {
			context.addEvaluationError(
					new EvaluationError(
							"Field does not contain expected modifier. Used modifier: '%s', expected: '%s'",
							Modifier.toString(modifiers), Modifier.toString(expectedModifier)
					)
			);

			return false;
		}

		return true;
	}
}
