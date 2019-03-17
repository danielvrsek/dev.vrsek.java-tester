package dev.vrsek.javatester.modules.inheritance;

import dev.vrsek.javatester.modules.EvaluationContext;
import dev.vrsek.javatester.modules.EvaluationError;
import dev.vrsek.javatester.modules.IEvaluationModule;
import dev.vrsek.javatester.modules.RootEvaluationContext;
import dev.vrsek.javatester.modules.inheritance.configuration.model.InheritanceModule;
import dev.vrsek.utils.reflect.ClassUtils;

public class InheritanceEvaluationModule implements IEvaluationModule<InheritanceModule> {
	public static final String MODULE_IDENTIFIER = "inheritance";

	@Override
	public Boolean canEvaluate(String key) {
		return key.equals(MODULE_IDENTIFIER);
	}

	@Override
	public void evaluate(InheritanceModule configuration, RootEvaluationContext rootContext) {
		EvaluationContext context = new EvaluationContext("inheritance");
		rootContext.addChildContext(context);

		Class evaluatedClass = rootContext.getEvaluatedClass();

		if (!ClassUtils.inheritsFromInterface(evaluatedClass, configuration.getInheritedType())
			&& !ClassUtils.inheritsFromClass(evaluatedClass, configuration.getInheritedType())) {
			context.addEvaluationError(
					new EvaluationError("Class '%s' does not inherit from '%s'.", evaluatedClass.getTypeName(), configuration.getInheritedType())
			);
		}
		// TODO: Error handling
	}
}
