package dev.vrsek.javatester.modules;

import java.util.ArrayList;
import java.util.List;

public class EvaluationErrorSerializer {
	public String serialize(IReadOnlyEvaluationContext context) {
		StringBuilder stringBuilder = new StringBuilder();

		for (var err : context.getEvaluationErrors()) {
			stringBuilder.append(String.format("%s: %s\n", getIdentifier(context), err.getMessage()));
		}

		for (var childContext : context.getChildContexts()) {
			stringBuilder.append(serialize(childContext));
		}

		return stringBuilder.toString();
	}

	private String getIdentifier(IReadOnlyEvaluationContext context) {
		List<String> identifiers = new ArrayList<>();
		identifiers.add(context.getIdentifier());

		IReadOnlyEvaluationContext parentContext = context.getParentContext();
		while (parentContext != null) {
			identifiers.add(parentContext.getIdentifier());
			parentContext = parentContext.getParentContext();
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = identifiers.size() - 1; i >= 0; i--) {
			if (i < identifiers.size() - 1) {
				stringBuilder.append(".");
			}

			stringBuilder.append(identifiers.get(i));
		}

		return stringBuilder.toString();
	}
}
