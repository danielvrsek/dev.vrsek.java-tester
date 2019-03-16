package dev.vrsek.utils.reflect;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ClassLoader {
	public static Class<?> load(String location) throws ClassNotFoundException {
		return DynamicURLClassLoader.getInstance().loadClass(location);
	}

	public static void addUrl(URL url) {
		DynamicURLClassLoader.getInstance().addURL(url);
	}

	public static void addDirectory(String directory) throws IOException {
		File dir = new File(directory);

		ArrayList<String> files = new ArrayList<>();

		ClassFileProcessor fileProcessor = new ClassFileProcessor();
		Files.walkFileTree(dir.toPath(), fileProcessor);

		DynamicURLClassLoader.getInstance().addURL(new File(directory).toURI().toURL());

		try {
			for (String className : fileProcessor.files) {
				Class.forName(className, true, DynamicURLClassLoader.getInstance());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Class defineClass(String name, byte[] bytes) {
		return DynamicURLClassLoader.getInstance().defineClass(name, bytes);
	}

	public static java.lang.ClassLoader getInstance() {
		return DynamicURLClassLoader.getInstance();
	}

	private static class ClassFileProcessor extends SimpleFileVisitor<Path> {
		private String rootDirectory = null;
		private String currentPackage = "";

		private List<String> files = new ArrayList<>();

		public List<String> getFiles() {
			return files;
		}

		@Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (file.toString().endsWith(".class") && !file.getFileName().toString().contains("$")) {
				String classIdentifier = file.getFileName().toString()
						.replace(".class", "");

				files.add(currentPackage + "." + classIdentifier);
			}

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			if (rootDirectory == null) {
				rootDirectory = dir.toString();
			} else {
				currentPackage = getRelativePath(dir.toString())
						.replace("\\", ".")
						.replace("/", ".");
			}

			return FileVisitResult.CONTINUE;
		}

		private String getRelativePath(String absolutePath) {
			return absolutePath.substring(rootDirectory.length() + 1);
		}
	};
}
