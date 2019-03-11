package dev.vrsek.utils.reflect;

import dev.vrsek.utils.ILocator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public abstract class GenericReflectionClassLocator<TKey, TValue> implements ILocator<TKey, TValue> {
	private final Class<TValue> valueClass;
	private final Function<TValue, TKey> keyFunc;
	private Map<TKey, TValue> dictionary;

	protected String packagePrefix;

	public GenericReflectionClassLocator(Class<TValue> valueClass, Function<TValue, TKey> keyFunc) {
		this.valueClass = valueClass;
		this.keyFunc = keyFunc;
		this.packagePrefix = "";
	}

	private void initialize() {
		dictionary = new HashMap<>();

		try {
			Set<Class<? extends TValue>> allClasses = ClassLocator.allAssignableFrom(valueClass);

			for (var c : allClasses) {
				TValue instance = ClassActivator.createNewInstance(c);
				assert instance != null;

				dictionary.put(keyFunc.apply(instance), instance);
			}
		} catch (IOException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TValue find(TKey identifier) {
		if (dictionary == null) {
			initialize();
		}

		return dictionary.get(identifier);
	}

	@Override
	public Collection<TValue> findAll() {
		if (dictionary == null) {
			initialize();
		}

		return dictionary.values();
	}
}
