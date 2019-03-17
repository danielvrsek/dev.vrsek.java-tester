package dev.vrsek.javatester.modules.external.method;

import dev.vrsek.javatester.modules.RootEvaluationContext;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodDefinition;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodEvaluation;
import dev.vrsek.utils.Logger;
import dev.vrsek.utils.reflect.ClassUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

// TODO: refactorization required
public class ExternalMethodEvaluatorSubModule {
	public void evaluate(Class externalClass, ExternalMethodEvaluation configuration, RootEvaluationContext context) {
		Class evaluatedClass = context.getEvaluatedClass();

		for (ExternalMethodDefinition definition : configuration.getExternalMethodDefinitions()) {
			Object evaluatedObjectInstance = null;
			Object externalClassInstance = null;
			try {
				evaluatedObjectInstance = evaluatedClass.getConstructor().newInstance();
				externalClassInstance = externalClass.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
				continue;
			}

			Method evaluatedMethod = ClassUtils.findMethod(evaluatedClass, definition.getResultOfMethod());
			if (evaluatedMethod == null) {
				Logger.logf("- Typ '%s' neobsahuje metodu '%s'", evaluatedClass.getTypeName(), definition.getResultOfMethod());
				continue;
			}
			Method testMethod = ClassUtils.findMethod(externalClass, definition.getEvaluationMethodName());
			if (testMethod == null) {
				Logger.logf("- Typ '%s' neobsahuje metodu '%s'", externalClass.getTypeName(), definition.getResultOfMethod());
				continue;
			}

			Object result = null;
			try {
				result = evaluatedMethod.invoke(evaluatedObjectInstance);
			} catch (InvocationTargetException e) {
				Logger.logf("- Volaná metoda '%s' typu '%s' vyhodila runtime vyjimku: %s", evaluatedMethod.getName(), evaluatedMethod.getDeclaringClass().getTypeName(), e.getCause().toString());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

			invokeExternalMethod(testMethod, externalClassInstance, result);
		}
	}

	// TODO: remove duplicate ExternalTestEvaluatorSubModule
	private void invokeExternalMethod(Method method, Object externalClassInstance, Object argument) {
		if (method.getParameterTypes().length != 1) {
			Logger.logf("- Neplatný počet parametrů uvedených v testovací metodě ve třídě '%s'", method.getDeclaringClass().getTypeName());
			return;
		}

		Class parameterType = Arrays.stream(method.getParameterTypes()).findFirst().get();

		if (!ClassUtils.inheritsFromInterface(argument.getClass(), parameterType.getTypeName(), true)
				&& !ClassUtils.inheritsFromClass(argument.getClass(), parameterType.getTypeName())) {

			Logger.logf("- Třída '%s' neimplementuje požadovaný interface nebo třídu '%s'", argument.getClass().getTypeName(), parameterType.getTypeName());
			return;
		}

		try {
			method.invoke(externalClassInstance, argument);
		} catch (InvocationTargetException e) {
			Logger.logf("- Externí metoda '%s' typu '%s' vyhodila runtime vyjimku: %s", method.getName(), method.getDeclaringClass().getTypeName(), e.getCause().toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
