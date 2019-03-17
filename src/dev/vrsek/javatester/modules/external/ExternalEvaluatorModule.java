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
import java.util.Collection;
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
		Collection<String> includeDirectories = configuration.getIncludeDirectories();

		assert classPath != null;

		for (String includeDirectory : includeDirectories) {
			if (includeDirectory != null && !includeDirectory.isEmpty()) {
				File dir = new File(includeDirectory);
				if (!dir.exists()) {
					context.addEvaluationError(
							new EvaluationError(String.format("Specified include directory '%s' was not found.", includeDirectory))
					);
					return;
				}
			}

			// TODO: check for include directory != null
			try {
				ClassLoader.addUrl(new File(includeDirectory).toURI().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		List<String> options = Arrays.asList("-classpath", serializeIncludeDirectories(includeDirectories));
		Class compiledClass = getClassDef(classPath, options);


		if (compiledClass == null) {
			// TODO: error handling
			context.addEvaluationError(
					new EvaluationError("Compilation error: " + classPath)
			);

			return;
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

	private String serializeIncludeDirectories(Collection<String> includeDirs) {
		StringBuilder sb = new StringBuilder();

		for (String includeDir : includeDirs) {
			sb.append(includeDir).append(";");
		}

		return sb.toString();
	}

	private Class getClassDef(String classPath, List<String> options) {
		String className = getClassName(classPath);

		Class compiledClass = null;
		try {
			compiledClass = Class.forName(className, false, ClassLoader.getInstance());
		} catch (ClassNotFoundException | ClassFormatError e) {
			//e.printStackTrace();
		}

		if (compiledClass != null) {
			return compiledClass;
		}

		InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();
		try {
			String classSource = getClassSource(classPath);

			return compiler.compile(className, classSource, options);
		} catch (Exception e) {
			// TODO: remove prntln
			e.printStackTrace();
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
