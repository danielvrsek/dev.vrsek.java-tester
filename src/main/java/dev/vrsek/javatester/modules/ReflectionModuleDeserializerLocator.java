package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.classtest.IModuleDeserializer;
import dev.vrsek.utils.reflect.ClassActivator;
import dev.vrsek.utils.reflect.ClassLocator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectionModuleDeserializerLocator implements IModuleDeserializerLocator {
	private Map<String, IModuleDeserializer> dictionary;

	private void initialize() {
		dictionary = new HashMap<>();

		try {
			Set<Class<? extends IModuleDeserializer>> allClasses = ClassLocator.allAssignableFrom(IModuleDeserializer.class);

			for (var c : allClasses) {
				IModuleDeserializer instance = ClassActivator.createNewInstance(c);
				assert instance != null;

				dictionary.put(instance.getModuleIdentifier(), instance);
			}
		} catch (IOException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IModuleDeserializer find(String identifier) {
		if (dictionary == null) {
			initialize();
		}

		return dictionary.get(identifier);
	}

	@Override
	public Collection<IModuleDeserializer> findAll() {
		if (dictionary == null) {
			initialize();
		}

		return dictionary.values();
	}
}
