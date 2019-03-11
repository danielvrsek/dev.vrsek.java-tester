package dev.vrsek.utils.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassActivator {
	public static <T> T createNewInstance(Class<T> type) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor parameterlessCtor = null;
		for (Constructor ctor : ctors) {
			if (ctor.getParameterCount() == 0) {
				parameterlessCtor = ctor;
				break;
			}
		}

		if (parameterlessCtor == null) {
			return null;
		}

		parameterlessCtor.setAccessible(true);
		return (T)parameterlessCtor.newInstance();
	}
}
