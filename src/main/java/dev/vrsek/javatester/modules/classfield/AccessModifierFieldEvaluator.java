package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.EvaluationError;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccessModifierFieldEvaluator implements IFieldEvaluator {

	private final Integer modifiers;
	private final Integer expectedModifier;

	public AccessModifierFieldEvaluator(Integer modifiers, Integer expectedModifier){
		this.modifiers = modifiers;
		this.expectedModifier = expectedModifier;
	}

	@Override
	public Collection<EvaluationError> evaluate() {
		List<EvaluationError> errors = new ArrayList<>();

		if ((expectedModifier & modifiers) != expectedModifier) {
			errors.add(
					new EvaluationError(
							"Field does not contain expected modifier. Used modifier: '%s', expected: '%s'",
							Modifier.toString(modifiers), Modifier.toString(expectedModifier)
					)
			);
		}

		return errors;
	}
}
