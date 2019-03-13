package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.model.ClassTestConfiguration;
import dev.vrsek.utils.reflect.ClassLoader;

public class EvaluationModuleExecutor {
	private final IEvaluationModuleLocator evaluationModuleLocator;

	public EvaluationModuleExecutor(IEvaluationModuleLocator evaluationModuleLocator) {
		this.evaluationModuleLocator = evaluationModuleLocator;
	}

	public void execute(ClassTestConfiguration classTestConfiguration) {
		RootEvaluationContext context = new RootEvaluationContext("");

		Class evaluatedClass = null;
		try {
			context.setEvaluatedClass(ClassLoader.load("dev.vrsek.TestClass"));
		} catch (ClassNotFoundException e) {
			context.addEvaluationError(
					new EvaluationError(String.format("Evaluated class '%s' was not found.", context.getEvaluatedClassLocation()))
			);
		}

		for (var module : classTestConfiguration.getModules()) {
			IEvaluationModule moduleEvaluator = evaluationModuleLocator.find(module.getKey());

			moduleEvaluator.evaluate(module.getValue(), context);
		}

		String errors = new EvaluationErrorSerializer().serialize(context);
		System.out.println(errors);
	}
}
