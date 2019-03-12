package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.classtest.model.Module;

public interface IEvaluationModule<T extends Module> {
	Boolean canEvaluate(String key);

	void evaluate(T configuration, RootEvaluationContext context);
}
