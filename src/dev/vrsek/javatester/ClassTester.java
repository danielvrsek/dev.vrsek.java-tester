package dev.vrsek.javatester;

import dev.vrsek.javatester.core.configuration.model.ClassTestConfiguration;
import dev.vrsek.javatester.modules.EvaluationModuleExecutor;
import dev.vrsek.javatester.modules.EvaluationModuleLocator;
import dev.vrsek.utils.Logger;
import dev.vrsek.utils.reflect.ClassLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassTester {
	private final String classDir;

	public ClassTester(String classDir, String submittedPackagesBase) throws MalformedURLException {
		this.classDir = classDir;

		ClassLoader.addUrl(new File(classDir).toURI().toURL());
	}

	protected void evaluate(String classIdentifier, ClassTestConfiguration configuration) {
		System.out.println("evaluating: " + classIdentifier);
		Class evaluatedClass = getClassDef(classIdentifier, ClassLoader.getInstance());

		if (evaluatedClass == null) {
			Logger.log(String.format("- Třída '%s' nebyla nalezena. Pravděpodobně má třída špatně nastavený balíček.", classIdentifier));
			return;
		}

		EvaluationModuleExecutor executor = new EvaluationModuleExecutor(new EvaluationModuleLocator());
		try {
			executor.execute(configuration, evaluatedClass);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean packageExists(String packageName) {
		Path dir = Paths.get(classDir, packageName.replace(".", "\\"));

		return dir.toFile().exists();
	}

	protected Class getClassDef(String classIdentifier, java.lang.ClassLoader loader) {
		try {
			return Class.forName(classIdentifier, true, loader);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find class: " + classIdentifier);
		} catch (NoClassDefFoundError e) {
			System.out.println("No class def found for: " + classIdentifier);
		}

		return null;
	}

	protected String readConfig(String filename) throws FileNotFoundException {
		// TODO: remove constants
		File cfg = new File(filename);

		StringBuilder sb = new StringBuilder();

		try (Scanner scanner = new Scanner(cfg)) {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			return sb.toString();
		}
	}

	protected Collection<String> readPackages(String filePath) throws FileNotFoundException {
		// TODO: remove constants
		File cfg = new File(filePath);

		List<String> lines = new ArrayList<>();

		try (Scanner scanner = new Scanner(cfg)) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		}

		return lines;
	}

	protected String getClassName(String pckg, String endsWith) {
		Path directory = Paths.get(classDir, pckg.replace(".", "\\"));

		File dir = directory.toFile();

		if (!dir.exists()) {
			return null;
		}

		File [] files = dir.listFiles((dir1, name) -> name.endsWith(endsWith));

		if (files == null) {
			return null;
		}

		return Arrays.stream(files).map(x -> pckg + "." + x.getName().replace(".class", "")).findFirst().orElse(null);
	}
}
