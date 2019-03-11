package dev.vrsek.javatester.services;

import dev.vrsek.javatester.modules.IEvaluationModule;
import dev.vrsek.javatester.modules.IEvaluationModuleLocator;
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
