package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.classtest.model.ClassTestConfiguration;

public class EvaluationModuleExecutor {
	private final IEvaluationModuleLocator evaluationModuleLocator;

	public EvaluationModuleExecutor(IEvaluationModuleLocator evaluationModuleLocator) {
		this.evaluationModuleLocator = evaluationModuleLocator;
	}

	public void execute(ClassTestConfiguration classTestConfiguration) {
		RootEvaluationContext context = new RootEvaluationContext("root");
		context.setEvaluatedClassLocation("dev.vrsek.TestClass");

		for (var module : classTestConfiguration.getModules()) {
			IEvaluationModule moduleEvaluator = evaluationModuleLocator.find(module.getKey());

			moduleEvaluator.evaluate(module.getValue(), context);
		}

		String errors = new EvaluationErrorSerializer().serialize(context);
		System.out.println(errors);
	}
}
