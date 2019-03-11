package dev.vrsek.javatester.modules.methodcall;

import dev.vrsek.javatester.modules.EvaluationContext;
import dev.vrsek.javatester.modules.EvaluationException;
import dev.vrsek.javatester.modules.IEvaluationModule;
import dev.vrsek.javatester.modules.methodcall.configuration.model.MethodCallModuleCollection;

public class MethodCallEvaluationModule implements IEvaluationModule<MethodCallModuleCollection> {
	public static final String MODULE_IDENTIFIER = "methodcall";

	@Override
	public Boolean canEvaluate(String key) {
		return key.equals(MODULE_IDENTIFIER);
	}

	@Override
	public void evaluate(MethodCallModuleCollection configuration, EvaluationContext context) throws EvaluationException {

	}
}
