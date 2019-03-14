package dev.vrsek.utils.reflect;

import java.net.URL;

public class ClassLoader {
	public static Class<?> load(String location) throws ClassNotFoundException {
		return DynamicURLClassLoader.getInstance().loadClass(location);
	}

	public static void addUrl(URL url) {
		DynamicURLClassLoader.getInstance().addURL(url);
	}

	public static Class defineClass(String name, byte[] bytes) {
		return DynamicURLClassLoader.getInstance().defineClass(name, bytes);
	}
}
