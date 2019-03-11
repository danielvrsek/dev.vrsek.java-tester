package dev.vrsek.utils.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassActivator {
	public static <T> T createNewInstance(Class<T> type) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}

		if (ctor == null) {
			return null;
		}

		ctor.setAccessible(true);
		return (T)ctor.newInstance();
	}
}
