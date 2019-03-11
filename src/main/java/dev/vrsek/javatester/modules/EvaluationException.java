package dev.vrsek.javatester.modules;

import java.util.Arrays;
import java.util.Collection;

public class EvaluationException extends Exception {
	public static EvaluationException create(EvaluationError... errors) {
		return create(Arrays.asList(errors));
	}

	public static EvaluationException create(Collection<EvaluationError> errors) {
		StringBuilder messageBuilder = new StringBuilder();

		for (EvaluationError error : errors) {
			messageBuilder.append(error.getMessage() + "\n");
		}

		return new EvaluationException(messageBuilder.toString());
	}

	public static EvaluationException fromInternalException(Exception e) {
		return new EvaluationException(e);
	}

	// Use when internal error occurs
	private EvaluationException(Exception inner) {
		super(inner);

		System.out.println("Internal error occured while evaluation.");
	}

	// General use
	private EvaluationException(String message) {
		super(message);
	}
}
