package dev.vrsek.utils.reflect;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;

public class DynamicURLClassLoader extends URLClassLoader {
	private static DynamicURLClassLoader instance;

	public static DynamicURLClassLoader getInstance() {
		if (instance == null) {
			instance = new DynamicURLClassLoader();
		}

		return instance;
	}

	private DynamicURLClassLoader() {
		super(new URL[0]);
	}

	@Override
	public void addURL(URL url) {
		super.addURL(url);
		System.out.println(url.toString());
	}

	public void addURLs(Enumeration<URL> urls) {
		Collections.list(urls).forEach(this::addURL);;
	}

	public Class defineClass(String name, byte[] bytes) {
		return super.defineClass(name, bytes, 0, bytes.length);
	}
}
