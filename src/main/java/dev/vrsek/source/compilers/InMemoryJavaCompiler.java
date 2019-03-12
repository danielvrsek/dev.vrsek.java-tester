package dev.vrsek.source.compilers;

import dev.vrsek.utils.reflect.DynamicURLClassLoader;

import javax.tools.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class InMemoryJavaCompiler {
	private final DefineClass defineClass;

	public InMemoryJavaCompiler() {
		this.defineClass = DynamicURLClassLoader.getInstance()::defineClass;
	}

	public InMemoryJavaCompiler(DefineClass defineClass) {
		this.defineClass = defineClass;
	}

	public Class compile(String className, String source) throws URISyntaxException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

		final JavaByteObject outputJavaObject = new JavaByteObject(className);
		StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);

		try {
			JavaFileManager fileManager = new JavaByteObjectFileManager(outputJavaObject, standardFileManager);
			JavaCompiler.CompilationTask task = compiler.getTask(null,
					fileManager, diagnostics, null, null, getCompilationUnits(className, source));

			if (!task.call()) {
				diagnostics.getDiagnostics().forEach(System.out::println);
			}
			fileManager.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return defineClass.defineClass(className, outputJavaObject.getBytes());
	}

	public Iterable<? extends JavaFileObject> getCompilationUnits(String className, String source) throws URISyntaxException, IOException {
		JavaStringObject stringObject = new JavaStringObject(className, source);
		return Arrays.asList(stringObject);
	}

	public interface DefineClass extends IExtendedClassLoader{
	}
}
