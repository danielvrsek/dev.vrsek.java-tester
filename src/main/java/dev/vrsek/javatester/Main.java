package dev.vrsek.javatester;

import dev.vrsek.javatester.core.configuration.classtest.ClassTestConfigurationDeserializer;

public class Main {
	public static void main(String[] args) {
		String json = "{\"evaluatedMethodName\":\"testMethod\",\"evaluationModules\":[{\"methodcall\":[{\"name\":\"someMethod_du_05_19\",\"description\":\"Domaci ukol - volani testovaci metody\",\"type\":\"test.package.DependentClass\",\"methods\":[{\"name\":\"someMethod\",\"parameters\":[{\"type\":\"java.lang.String\",\"name\":\"param1\"},{\"type\":\"java.lang.Integer\",\"name\":\"param2\"}],\"callCount\":1,\"evaluationBehavior\":\"strict\"}]}]},{\"classfield\":[{\"name\":\"testField_du_05_19\",\"description\":\"Domaci ukol - existence privatniho atributu\",\"fields\":[{\"evaluation\":\"exists\",\"name\":\"testField\",\"type\":\"java.lang.String\",\"accessModifier\":\"private\"}]}]}]}";

		ClassTestConfigurationDeserializer deserializer = new ClassTestConfigurationDeserializer();
		var out = deserializer.deserialize(json);
	}
}
