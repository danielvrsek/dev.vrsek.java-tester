package dev.vrsek.javatester.modules.external.test;

import dev.vrsek.javatester.modules.RootEvaluationContext;
import dev.vrsek.javatester.modules.external.test.configuration.model.ExternalTestDefinition;
import dev.vrsek.javatester.modules.external.test.configuration.model.ExternalTestEvaluation;
import dev.vrsek.utils.Logger;
import dev.vrsek.utils.reflect.ClassUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO: implements interface SubModuleEvaluator
public class ExternalTestEvaluatorSubModule {
	public void evaluate(Class externalClass, ExternalTestEvaluation configuration, RootEvaluationContext context) {
		Class evaluatedClass = context.getEvaluatedClass();

		// TODO: Error handling
		Object externalClassInstance = null, evaluatedClassInstance = null;
		try {
			evaluatedClassInstance = evaluatedClass.getConstructor().newInstance();
			externalClassInstance = externalClass.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}

		// TODO: handling for situations where more setups or befores are present
		Method[] methods = evaluatedClass.getMethods();
		Method setupMethod = Arrays.stream(methods).filter(x -> x.getDeclaredAnnotation(Before.class) != null).findFirst().orElse(null);
		List<Method> testMethods = Arrays.stream(methods).filter(x -> x.isAnnotationPresent(Test.class)).collect(Collectors.toList());
		Method tearDownMethod = Arrays.stream(methods).filter(x -> x.isAnnotationPresent(After.class)).findFirst().orElse(null);

		for (ExternalTestDefinition definition : configuration.getExternalTestDefinitions()) {

			Method testMethod = testMethods.stream().filter(x -> x.getName().equals(definition.getTestMethodName())).findFirst().orElse(null);

			// TODO: check for setup and teardown
			if (definition.getBeforeSetup() != null) {
				if (setupMethod == null) {
					Logger.log(String.format("- Třída '%s' neobsahuje metodu s anotací @Before", evaluatedClass.getTypeName()));
				} else {
					// TODO: check for null
					Method method = ClassUtils.findMethod(externalClass, definition.getBeforeSetup());
					invokeExternalMethod(method, externalClassInstance, evaluatedClassInstance);
				}
			}

			// Invoke setup
			if (setupMethod == null){
				Logger.logf("- Třída '%s' neobsahuje motodu s anotací @Before", evaluatedClass.getTypeName());
				continue;
			}
			invokeEvaluatedMethod(setupMethod, evaluatedClassInstance);

			if (definition.getBeforeTest() != null) {
				// TODO: check for null
				Method method = ClassUtils.findMethod(externalClass, definition.getBeforeTest());
				invokeExternalMethod(method, externalClassInstance, evaluatedClassInstance);
			}

			// Invoke test
			if (testMethod == null) {
				Logger.log(String.format("- Třída '%s' neobsahuje metodu '%s'", evaluatedClass.getTypeName(), definition.getTestMethodName()));
				continue;
			}
			invokeEvaluatedMethod(testMethod, evaluatedClassInstance);

			if (definition.getAfterTest() != null) {
				// TODO: check for null
				Method method = ClassUtils.findMethod(externalClass, definition.getAfterTest());
				invokeExternalMethod(method, externalClassInstance, evaluatedClassInstance);
			}

			if (tearDownMethod == null) {
				Logger.logf("- Třída '%s' neobsahuje motodu s anotací @After", evaluatedClass.getTypeName());
				continue;
			}
			invokeEvaluatedMethod(tearDownMethod, evaluatedClassInstance);
		}
	}

	private void invokeEvaluatedMethod(Method method, Object evaluatedClassInstance) {
		try {
			method.invoke(evaluatedClassInstance);
		} catch (InvocationTargetException e) {
			Logger.logf("- Volaná metoda '%s' typu '%s' vyhodila runtime vyjimku: %s", method.getName(), method.getDeclaringClass().getTypeName(), e.getCause().toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void invokeExternalMethod(Method method, Object externalClassInstance, Object evaluatedClassInstance) {
		if (method.getParameterTypes().length != 1) {
			Logger.logf("- Neplatný počet parametrů uvedených v testovací metodě ve třídě '%s'", method.getDeclaringClass().getTypeName());
			return;
		}

		Class parameterType = Arrays.stream(method.getParameterTypes()).findFirst().get();

		if (!ClassUtils.inheritsFromInterface(evaluatedClassInstance.getClass(), parameterType.getTypeName(), true)
				&& !ClassUtils.inheritsFromClass(evaluatedClassInstance.getClass(), parameterType.getTypeName())) {

			Logger.logf("- Třída '%s' neimplementuje požadovaný interface nebo třídu '%s'", evaluatedClassInstance.getClass().getTypeName(), parameterType.getTypeName());
			return;
		}

		try {
			method.invoke(externalClassInstance, evaluatedClassInstance);
		} catch (InvocationTargetException e) {
			Logger.logf("- Externí metoda '%s' typu '%s' vyhodila runtime vyjimku: %s", method.getName(), method.getDeclaringClass().getTypeName(), e.getCause().toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
