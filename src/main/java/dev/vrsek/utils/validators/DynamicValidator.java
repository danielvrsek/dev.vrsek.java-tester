package dev.vrsek.utils.validators;

import java.util.function.Supplier;

public abstract class DynamicValidator<T> extends ValidatorBase {
	private final Supplier<T> supplier;

	protected DynamicValidator(Supplier<T> supplier, String errorMessage) {
		super(errorMessage);

		this.supplier = supplier;
	}

	protected T getValue() {
		return supplier.get();
	}
}
