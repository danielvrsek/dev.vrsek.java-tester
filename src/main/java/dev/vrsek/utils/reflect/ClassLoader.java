package dev.vrsek.utils.reflect;

public class ClassLoader {
	public static Class<?> load(String location) throws ClassNotFoundException {
		return java.lang.ClassLoader.getSystemClassLoader().loadClass(location);
	}
}
