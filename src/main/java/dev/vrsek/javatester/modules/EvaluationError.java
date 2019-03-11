package dev.vrsek.javatester.modules;

public class EvaluationError {
	private String message;

	public EvaluationError(String message) {
		this.message = message;
	}

	public EvaluationError(String message, String... args) {
		this.message = String.format(message, args);
	}

	public String getMessage() {
		return message;
	}
}
