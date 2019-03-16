package dev.vrsek.javatester.modules.inheritance;

import dev.vrsek.javatester.modules.EvaluationContext;
import dev.vrsek.javatester.modules.EvaluationError;
import dev.vrsek.javatester.modules.IEvaluationModule;
import dev.vrsek.javatester.modules.RootEvaluationContext;
import dev.vrsek.javatester.modules.inheritance.configuration.model.InheritanceModule;

import java.util.Arrays;
import java.util.List;

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

		if (!inheritsFromInterface(evaluatedClass, configuration.getInheritedType())
			&& !inheritsFromClass(evaluatedClass, configuration.getInheritedType())) {
			context.addEvaluationError(
					new EvaluationError("Class '%s' does not inherit from '%s'.", evaluatedClass.getTypeName(), configuration.getInheritedType())
			);
		}
		// TODO: Error handling
	}

	private boolean inheritsFromInterface(Class evaluatedClass, String interfaceType) {
		List<Class> interfaces = Arrays.asList(evaluatedClass.getInterfaces());

		return interfaces.stream().anyMatch(x -> x.getTypeName().equals(interfaceType));
	}

	private boolean inheritsFromClass(Class evaluatedClass, String classType) {
		Class superClass = evaluatedClass.getSuperclass();

		return superClass.getTypeName().equals(classType);
	}
}
