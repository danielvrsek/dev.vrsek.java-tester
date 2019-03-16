package dev.vrsek.javatester;

import dev.vrsek.javatester.core.configuration.ClassTestConfigurationDeserializer;
import dev.vrsek.javatester.core.configuration.model.ClassTestConfiguration;
import dev.vrsek.javatester.modules.EvaluationModuleExecutor;
import dev.vrsek.javatester.modules.ReflectionEvaluationModuleLocator;
import dev.vrsek.utils.Logger;
import dev.vrsek.utils.reflect.ClassLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ClassTester {
	private static String submittedPackagesBase = "eu.pedu.ofpa1_19s";
	private static String classDir = "C:\\Tests\\build\\classes\\";

	public static void main(String[] args) throws Exception {
		ClassTestConfigurationDeserializer deserializer = new ClassTestConfigurationDeserializer();
		ClassTestConfiguration configuration = deserializer.deserialize(readConfig());

		ClassLoader.addUrl(new File(classDir).toURI().toURL());

		Collection<String> submittedPackages = readPackages();

		for (String submittedPackage : submittedPackages) {
			String fullPackageName = submittedPackagesBase + "." + submittedPackage;

			if (!packageExists(fullPackageName)) {
				Logger.log("- Spatny nazev balicku");
				continue;
			}

			String classIdentifier = getFactoryClass(fullPackageName);
			if (classIdentifier == null) {
				Logger.log("- V balicku se nenachazi tovarni trida nebo je ve spatnem balicku.");
				continue;
			}

			System.out.println("evaluating: " + classIdentifier);
			Class evaluatedClass = getClassDef(classIdentifier, ClassLoader.getInstance());

			if (evaluatedClass == null) {
				continue;
			}

			EvaluationModuleExecutor executor = new EvaluationModuleExecutor(new ReflectionEvaluationModuleLocator());
			try {
				executor.execute(configuration, evaluatedClass);
			} catch(Exception e) {
				e.printStackTrace();
			}

			Logger logger = Logger.refresh();

			for (String message : logger.getMessages()) {
				System.out.println(message);
			}
		}
	}

	private static boolean packageExists(String packageName) {
		Path dir = Paths.get(classDir, packageName.replace(".", "\\"));

		return dir.toFile().exists();
	}

	private static Class getClassDef(String classIdentifier, java.lang.ClassLoader loader) {
		try {
			return Class.forName(classIdentifier, true, loader);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find class: " + classIdentifier);
		} catch (NoClassDefFoundError e) {
			System.out.println("No class def found for: " + classIdentifier);
		}

		return null;
	}

	private static String readConfig() throws FileNotFoundException {
		// TODO: remove constants
		File cfg = new File("testclass.config.json");

		StringBuilder sb = new StringBuilder();

		try (Scanner scanner = new Scanner(cfg)) {
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
			}
			return sb.toString();
		}
	}

	private static Collection<String> readPackages() throws FileNotFoundException {
		// TODO: remove constants
		File cfg = new File("submittedPackages.txt");

		List<String> lines = new ArrayList<>();

		try (Scanner scanner = new Scanner(cfg)) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		}

		return lines;
	}

	private static Collection<String> parsePackages(Collection<String> lines) {
		List<String> packageNames = new ArrayList<>();

		String parentPackage = null;

		for (String line : lines) {
			if (line.startsWith(":")) {
				parentPackage = line.substring(1);
				continue;
			}

			packageNames.add(parentPackage + "/" + line);
		}

		return packageNames;
	}

	private static String getFactoryClass(String pckg) {
		Path directory = Paths.get(classDir, pckg.replace(".", "\\"));

		File dir = directory.toFile();

		if (!dir.exists()) {
			return null;
		}

		File [] files = dir.listFiles((dir1, name) -> name.endsWith("Factory.class"));

		return Arrays.stream(files).map(x -> pckg + "." + x.getName().replace(".class", "")).findFirst().orElse(null);
	}
}
