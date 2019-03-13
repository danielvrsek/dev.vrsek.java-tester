package dev.vrsek.javatester;

import dev.vrsek.javatester.core.configuration.model.ClassTestConfiguration;
import dev.vrsek.javatester.core.configuration.model.ClassTestConfigurationDeserializer;
import dev.vrsek.javatester.modules.EvaluationModuleExecutor;
import dev.vrsek.javatester.modules.ReflectionEvaluationModuleLocator;

public class JavaTester {
	public static void main(String[] args) throws Exception {
		String json = "{\"evaluatedMethodName\":\"testMethod\",\"evaluationModules\":[{\"methodcall\":[{\"name\":\"someMethod_du_05_19\",\"description\":\"Domaci ukol - volani testovaci metody\",\"type\":\"test.package.DependentClass\",\"methods\":[{\"name\":\"someMethod\",\"parameters\":[{\"type\":\"java.lang.String\",\"name\":\"param1\"},{\"type\":\"java.lang.Integer\",\"name\":\"param2\"}],\"callCount\":1,\"evaluationBehavior\":\"strict\"}]}]},{\"classfield\":[{\"name\":\"testField_du_05_19\",\"description\":\"Domaci ukol - existence privatniho atributu\",\"fields\":[{\"evaluation\":\"EXISTS\",\"name\":\"testField\",\"type\":\"java.lang.String\",\"accessModifier\":\"PRIVATE\"}]}]}]}";

		ClassTestConfigurationDeserializer deserializer = new ClassTestConfigurationDeserializer();
		ClassTestConfiguration configuration = deserializer.deserialize(json);

		EvaluationModuleExecutor executor = new EvaluationModuleExecutor(new ReflectionEvaluationModuleLocator());
		executor.execute(configuration);
	}
}
