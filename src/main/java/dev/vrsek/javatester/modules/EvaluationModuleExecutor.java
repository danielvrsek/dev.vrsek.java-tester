package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.classtest.model.ClassTestConfiguration;

public class EvaluationModuleExecutor {
	private final IEvaluationModuleLocator evaluationModuleLocator;

	public EvaluationModuleExecutor(IEvaluationModuleLocator evaluationModuleLocator) {
		this.evaluationModuleLocator = evaluationModuleLocator;
	}

	public void execute(ClassTestConfiguration classTestConfiguration) {
		EvaluationContext context = new EvaluationContext();
		context.setEvaluatedClassLocation("TestClass");

		for (var module : classTestConfiguration.getModules()) {
			IEvaluationModule moduleEvaluator = evaluationModuleLocator.find(module.getKey());

			try {
				moduleEvaluator.evaluate(module.getValue(), context);
			} catch (EvaluationException e) {
				e.printStackTrace();
			}
		}
	}
}
