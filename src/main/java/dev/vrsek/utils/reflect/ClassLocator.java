package dev.vrsek.utils.reflect;

import org.reflections.Reflections;

import java.io.IOException;
import java.util.Set;

public class ClassLocator {
	public static <T> Set<Class<? extends T>> allAssignableFrom(Class<T> type) throws IOException {
		Reflections reflections = new Reflections("dev.vrsek.javatester.modules");

		return reflections.getSubTypesOf(type);
	}
}
