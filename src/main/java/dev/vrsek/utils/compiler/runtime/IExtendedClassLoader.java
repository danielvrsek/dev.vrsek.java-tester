package dev.vrsek.utils.compiler.runtime;

public interface IExtendedClassLoader {
	Class defineClass(String name, byte[] bytes);
}
