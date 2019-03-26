package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.modules.classfield.ClassFieldEvaluationModule;
import dev.vrsek.javatester.modules.external.ExternalEvaluatorModule;
import dev.vrsek.javatester.modules.inheritance.InheritanceEvaluationModule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EvaluationModuleLocator implements IEvaluationModuleLocator  {
	private Map<String, IEvaluationModule> evaluationModuleMap;

	private void initialize() {
		evaluationModuleMap = new HashMap<>();
		evaluationModuleMap.put(ClassFieldEvaluationModule.MODULE_IDENTIFIER, new ClassFieldEvaluationModule());
		evaluationModuleMap.put(ExternalEvaluatorModule.MODULE_IDETIFIER, new ExternalEvaluatorModule());
		evaluationModuleMap.put(InheritanceEvaluationModule.MODULE_IDENTIFIER, new InheritanceEvaluationModule());
	}

	@Override
	public IEvaluationModule find(String identifier) {
		if (evaluationModuleMap == null) {
			initialize();
		}

		return evaluationModuleMap.get(identifier);
	}

	@Override
	public Collection<IEvaluationModule> findAll() {
		if (evaluationModuleMap == null) {
			initialize();
		}

		return evaluationModuleMap.values();
	}
}
