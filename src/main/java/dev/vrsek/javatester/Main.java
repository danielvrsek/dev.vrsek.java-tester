package dev.vrsek.javatester;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import dev.vrsek.javatester.core.serializers.source.AccessModifierToStringMapper;
import dev.vrsek.javatester.core.serializers.source.JavaClassSourceSerializer;
import dev.vrsek.javatester.core.serializers.source.JavaMethodSourceSerializer;
import dev.vrsek.javatester.core.serializers.source.model.AccessModifier;

public class Main {
	public static void main(String[] args) {
		JavaClassSourceSerializer classSourceSerializer = new JavaClassSourceSerializer(new AccessModifierToStringMapper());

		classSourceSerializer.setAccessModifier(AccessModifier.PUBLIC);
		classSourceSerializer.setPackageName("vrsek.test");
		classSourceSerializer.setClassName("TestClass");
		classSourceSerializer.addMembers(getTestMethod());

		try {
			System.out.println(new Formatter().formatSource(classSourceSerializer.serialize()));
		} catch (FormatterException e) {
			e.printStackTrace();
		}
	}

	private static JavaMethodSourceSerializer getTestMethod() {
		JavaMethodSourceSerializer serializer = new JavaMethodSourceSerializer(new AccessModifierToStringMapper());

		serializer.setAccessModifier(AccessModifier.PRIVATE);
		serializer.setTypeName("void");
		serializer.setName("testMethod");

		return serializer;
	}
}
