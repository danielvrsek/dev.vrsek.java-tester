package dev.vrsek.utils.reflect;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.Enumeration;

public class DynamicURLClassLoader extends URLClassLoader {
	private static DynamicURLClassLoader instance;

	protected static DynamicURLClassLoader getInstance() {
		if (instance == null) {
			instance = new DynamicURLClassLoader();
		}

		return instance;
	}

	private DynamicURLClassLoader() {
		super(new URL[0], java.lang.ClassLoader.getSystemClassLoader());
	}

	private DynamicURLClassLoader(URL url) {
		super(new URL[] { url });
	}

	@Override
	public URL getResource(String name) {
		return super.getResource(name);
	}

	@Override
	public InputStream getResourceAsStream(String name) {
		return super.getResourceAsStream(name);
	}

	@Override
	public void addURL(URL url) {
		super.addURL(url);
	}

	public void addURLs(Enumeration<URL> urls) {
		Collections.list(urls).forEach(this::addURL);;
	}

	public Class defineClass(String name, byte[] bytes) {
		return super.defineClass(name, bytes, 0, bytes.length);
	}
}
