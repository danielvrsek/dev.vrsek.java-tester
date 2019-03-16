package dev.vrsek.utils.reflect;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.io.IOException;
import java.util.Set;

public class ClassLocator {
	public static <T> Set<Class<? extends T>> allAssignableFrom(Class<T> type) throws IOException {
		return allAssignableFrom(type, "");
	}

	public static <T> Set<Class<? extends T>> allAssignableFrom(Class<T> type, String packagePrefix) throws IOException {
		assert packagePrefix != null;

		Reflections reflections = new Reflections(new ConfigurationBuilder()
			.forPackages(packagePrefix)
			.addClassLoader(DynamicURLClassLoader.getInstance()));

		return reflections.getSubTypesOf(type);
	}
}
