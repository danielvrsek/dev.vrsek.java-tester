package dev.vrsek.javatester.modules;

import dev.vrsek.utils.reflect.GenericReflectionClassLocator;

public class ReflectionEvaluationModuleLocator  extends GenericReflectionClassLocator<String, IEvaluationModule> implements IEvaluationModuleLocator {

	public ReflectionEvaluationModuleLocator() {
		super(IEvaluationModule.class, x -> x.getClass().getName());
		this.packagePrefix = "dev.vrsek.javatester.modules";
	}

	@Override
	public IEvaluationModule find(String identifier) {
		return findAll().stream()
				.filter(x -> x.canEvaluate(identifier))
				.findFirst().orElse(null);
	}
}
