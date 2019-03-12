package dev.vrsek.utils.reflect;

public class ClassLoader {
	public static Class<?> load(String location) throws ClassNotFoundException {
		return DynamicURLClassLoader.getInstance().loadClass(location);
	}
}
