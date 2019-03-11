package dev.vrsek.javatester.core.source.serializers.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface Serializable {
	String text();
}
