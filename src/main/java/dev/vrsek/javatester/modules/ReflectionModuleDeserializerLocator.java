package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.classtest.IModuleDeserializer;
import dev.vrsek.utils.reflect.GenericReflectionClassLocator;

public class ReflectionModuleDeserializerLocator extends GenericReflectionClassLocator<String, IModuleDeserializer> implements IModuleDeserializerLocator {
	public ReflectionModuleDeserializerLocator() {
		super(IModuleDeserializer.class, IModuleDeserializer::getModuleIdentifier);

		this.packagePrefix = "dev.vrsek.javatester.modules";
	}
}
