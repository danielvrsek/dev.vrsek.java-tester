package dev.vrsek.javatester.modules.external;

import dev.vrsek.javatester.modules.EvaluationError;
import dev.vrsek.javatester.modules.IEvaluationModule;
import dev.vrsek.javatester.modules.RootEvaluationContext;
import dev.vrsek.javatester.modules.external.configuration.model.ExternalEvaluation;
import dev.vrsek.javatester.modules.external.method.ExternalMethodEvaluatorSubModule;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodEvaluation;
import dev.vrsek.javatester.modules.external.test.ExternalTestEvaluatorSubModule;
import dev.vrsek.javatester.modules.external.test.configuration.model.ExternalTestEvaluation;
import dev.vrsek.source.compilers.InMemoryJavaCompiler;
import dev.vrsek.utils.reflect.ClassLoader;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExternalEvaluatorModule implements IEvaluationModule<ExternalEvaluation> {
	public static final String MODULE_IDETIFIER = "external";


	@Override
	public Boolean canEvaluate(String key) {
		return key.equals(MODULE_IDETIFIER);
	}

	@Override
	public void evaluate(ExternalEvaluation configuration, RootEvaluationContext context) {
		String classPath = configuration.getClassPath();
		String includeDirectory = configuration.getIncludeDirectory();

		assert classPath != null;

		if (includeDirectory != null && !includeDirectory.isEmpty()) {
			File dir = new File(includeDirectory);
			if (!dir.exists()) {
				context.addEvaluationError(
						new EvaluationError(String.format("Specified include directory '%s' was not found.", includeDirectory))
				);
				return;
			}
		}
		// TODO: Divide into methods

		// TODO: check for include directory != null
		List<String> options = Arrays.asList("-classpath", includeDirectory);
		try {
			ClassLoader.addUrl(new File(includeDirectory).toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();

		String className = getClassName(classPath);
		Class compiledClass = getClassDef(className);

		if (compiledClass == null) {
			try {
				String classSource = getClassSource(classPath);

				compiledClass = compiler.compile(className, classSource, options);
			} catch (Exception e) {
				// TODO: remove prntln
				e.printStackTrace();

				// TODO: error handling
				context.addEvaluationError(
						new EvaluationError(e.getMessage())
				);
				return;
			}
		}

		for (var module : configuration.getSubModules()) {
			// TODO: proper submodule lookup
			if (module.getKey().equals("method")) {
				var moduleEvaluator = new ExternalMethodEvaluatorSubModule();
				// TODO: new evaluation context
				moduleEvaluator.evaluate(compiledClass, (ExternalMethodEvaluation)module.getValue(), context);
			} else if (module.getKey().equals("test")) {
				var moduleEvaluator = new ExternalTestEvaluatorSubModule();
				moduleEvaluator.evaluate(compiledClass, (ExternalTestEvaluation)module.getValue(), context);
			}
		}
	}

	private Class getClassDef(String className) {
		try {
			return Class.forName(className, false, ClassLoader.getInstance());
		} catch (ClassNotFoundException e) {
			//e.printStackTrace();
		}

		return null;
	}

	// TODO: remove duplicates from EvaluationModuleExecutor
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
