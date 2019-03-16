package dev.vrsek.javatester.modules.external.method;

import dev.vrsek.javatester.modules.RootEvaluationContext;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodEvaluation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

// TODO: refactorization required
public class ExternalMethodEvaluatorSubModule {
	public void evaluate(Class externalClass, ExternalMethodEvaluation configuration, RootEvaluationContext context) {
		try {
			Class evaluatedClass = context.getEvaluatedClass();

			Object evaluatedObject = evaluatedClass.getConstructor().newInstance();
			Object testObject = externalClass.getConstructor().newInstance();

			Method evaluatedMethod = findMethod(evaluatedClass, configuration.getResultOfMethod());
			Method testMethod = findMethod(externalClass, configuration.getEvaluationMethodName());

			Object result = evaluatedMethod.invoke(evaluatedObject);
			testMethod.invoke(testObject, result);
		} catch (NoSuchMethodException e) {
			// TODO: Error handling - evaluation error
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO: Error handling - evaluation error
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO: Error handling - evaluation error
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		System.out.println(configuration.getEvaluationMethodName());
	}

	private Method findMethod(Class type, String name) {
		// TODO: refactorization
		return Arrays.stream(type.getMethods())
				.filter(x -> x.getName().equals(name))
				.findFirst().get();
	}
}
