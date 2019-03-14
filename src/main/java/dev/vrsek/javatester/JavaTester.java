package dev.vrsek.javatester;

import dev.vrsek.javatester.core.configuration.ClassTestConfigurationDeserializer;
import dev.vrsek.javatester.core.configuration.model.ClassTestConfiguration;
import dev.vrsek.javatester.modules.EvaluationModuleExecutor;
import dev.vrsek.javatester.modules.ReflectionEvaluationModuleLocator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JavaTester {
	public static void main(String[] args) throws Exception {
		ClassTestConfigurationDeserializer deserializer = new ClassTestConfigurationDeserializer();
		ClassTestConfiguration configuration = deserializer.deserialize(readConfig());

		EvaluationModuleExecutor executor = new EvaluationModuleExecutor(new ReflectionEvaluationModuleLocator());
		executor.execute(configuration);
	}

	private static String readConfig() throws FileNotFoundException {
		File cfg = new File("testclass.config.json");

		StringBuilder sb = new StringBuilder();

		try (Scanner scanner = new Scanner(cfg)) {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			return sb.toString();
		}
	}
}
