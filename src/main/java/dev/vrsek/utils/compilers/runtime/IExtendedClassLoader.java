package dev.vrsek.utils.compilers.runtime;

public interface IExtendedClassLoader {
	Class defineClass(String name, byte[] bytes);
}
