package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.model.ClassTestConfiguration;
import dev.vrsek.source.compilers.InMemoryJavaCompiler;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EvaluationModuleExecutor {
	private final IEvaluationModuleLocator evaluationModuleLocator;

	public static java.lang.ClassLoader classLoader;

	public EvaluationModuleExecutor(IEvaluationModuleLocator evaluationModuleLocator) {
		this.evaluationModuleLocator = evaluationModuleLocator;
	}

	public void execute(ClassTestConfiguration classTestConfiguration) throws FileNotFoundException, MalformedURLException, URISyntaxException, ClassNotFoundException {
		RootEvaluationContext context = new RootEvaluationContext("");
		// TODO: Test if class path ends with .java

		String classPath = classTestConfiguration.getClassPath();
		String includeDirectory = classTestConfiguration.getIncludeDirectory();

		assert classPath != null;

		if (includeDirectory != null && !includeDirectory.isEmpty()) {
			File dir = new File(includeDirectory);
			if (!dir.exists()) {
				throw new FileNotFoundException(String.format("Specified include directory '%s' was not found.", includeDirectory));
			}
		}
		// TODO: Divide into methods

		List<String> options = Arrays.asList("-classpath", includeDirectory);

		InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();
		Class compiledClass = compiler.compile(getClassName(classPath), getClassSource(classPath), options);

		context.setEvaluatedClass(compiledClass);

		for (var module : classTestConfiguration.getModules()) {
			IEvaluationModule moduleEvaluator = evaluationModuleLocator.find(module.getKey());

			moduleEvaluator.evaluate(module.getValue(), context);
		}

		String errors = new EvaluationErrorSerializer().serialize(context);
		System.out.println(errors);
	}

	private String getClassName(String filePath) {
		return FilenameUtils.getBaseName(filePath);
	}

	private String getClassSource(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		StringBuilder fileContents = new StringBuilder((int)file.length());

		try (Scanner scanner = new Scanner(file)) {
			while(scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine())
						.append(System.lineSeparator());
			}
			return fileContents.toString();
		}
	}
}
