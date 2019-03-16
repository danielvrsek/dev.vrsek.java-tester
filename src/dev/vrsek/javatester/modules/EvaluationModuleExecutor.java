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

	public void execute(ClassTestConfiguration classTestConfiguration, Class evaluatedClass) throws FileNotFoundException, MalformedURLException, URISyntaxException, ClassNotFoundException {
		RootEvaluationContext context = new RootEvaluationContext("");
		context.setEvaluatedClass(evaluatedClass);

		for (var module : classTestConfiguration.getModules()) {
			IEvaluationModule moduleEvaluator = evaluationModuleLocator.find(module.getKey());

			moduleEvaluator.evaluate(module.getValue(), context);
		}


		String errors = new EvaluationErrorSerializer().serialize(context);
		System.out.println(errors);
	}

	public void execute(ClassTestConfiguration classTestConfiguration, String evaluatedClassLocation, String[] includeDirectories) throws FileNotFoundException, MalformedURLException, URISyntaxException, ClassNotFoundException {
		RootEvaluationContext context = new RootEvaluationContext("");
		// TODO: Test if class path ends with .java

		// Validate that include directories exist
		for (String includeDirectory : includeDirectories) {
			if (!validateIncludeDirectory(includeDirectory)) {
				throw new FileNotFoundException(String.format("Specified include directory '%s' was not found.", includeDirectory));
			}
		}

		// TODO: Divide into methods

		List<String> options = Arrays.asList("-classpath", serializeIncludeDirectories(includeDirectories));

		InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();
		Class compiledClass = compiler.compile(getClassName(evaluatedClassLocation), getClassSource(evaluatedClassLocation), options);

		if (compiledClass != null) {
			context.setEvaluatedClass(compiledClass);

			for (var module : classTestConfiguration.getModules()) {
				IEvaluationModule moduleEvaluator = evaluationModuleLocator.find(module.getKey());

				moduleEvaluator.evaluate(module.getValue(), context);
			}
		} else {
			context.addEvaluationError(
					new EvaluationError("Compilation error.")
			);
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

	private String serializeIncludeDirectories(String[] includeDirectories) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < includeDirectories.length; i++) {
			if (i != 0) {
				stringBuilder.append(";");
			}

			stringBuilder.append(includeDirectories[i]);
		}

		return stringBuilder.toString();
	}

	private boolean validateIncludeDirectory(String includeDirectory) {
		if (includeDirectory != null && !includeDirectory.isEmpty()) {
			File dir = new File(includeDirectory);
			return dir.exists();
		}

		return false;
	}
}
