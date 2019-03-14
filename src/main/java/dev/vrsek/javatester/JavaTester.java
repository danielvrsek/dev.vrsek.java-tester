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

		String classPath = "C:\\Dev\\java-tester\\TestClass.java";
		String includeDirectory = "C:\\Tests\\05D_OFPA1_19s_HW_PKG\\OFPA1_19s_HW_PKG\\OFPA1_19s_HW_PKG_SRC\\build\\classes\\";

		executor.execute(configuration, classPath, new String[] { includeDirectory });
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
