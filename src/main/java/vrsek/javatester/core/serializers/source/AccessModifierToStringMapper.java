package vrsek.javatester.core.serializers.source;

import jdk.jshell.spi.ExecutionControl;
import vrsek.javatester.core.serializers.source.model.AccessModifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccessModifierToStringMapper implements IMapper<AccessModifier, String> {
	@Override
	public String map(AccessModifier input) {
		switch (input) {
			case PRIVATE:
				return "private";
			case PROTECTED:
				return "protected";
			case PUBLIC:
				return "public";
			default:
				return null;
		}
	}
}
