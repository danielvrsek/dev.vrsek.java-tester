package dev.vrsek.javatester;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import dev.vrsek.javatester.core.source.serializers.AccessModifierToStringMapper;
import dev.vrsek.javatester.core.source.serializers.JavaClassSourceBuilder;
import dev.vrsek.javatester.core.source.serializers.JavaMethodSourceBuilder;
import dev.vrsek.javatester.core.source.serializers.model.AccessModifier;

public class Main {
	public static void main(String[] args) {
		JavaClassSourceBuilder classSourceSerializer = new JavaClassSourceBuilder(new AccessModifierToStringMapper());

		classSourceSerializer.setAccessModifier(AccessModifier.PUBLIC);
		classSourceSerializer.setPackageName("vrsek.test");
		classSourceSerializer.setClassName("TestClass");
		classSourceSerializer.addMembers(getTestMethod());

		try {
			System.out.println(new Formatter().formatSource(classSourceSerializer.build()));
		} catch (FormatterException e) {
			e.printStackTrace();
		}
	}

	private static JavaMethodSourceBuilder getTestMethod() {
		JavaMethodSourceBuilder serializer = new JavaMethodSourceBuilder(new AccessModifierToStringMapper());

		serializer.setAccessModifier(AccessModifier.PRIVATE);
		serializer.setTypeName("void");
		serializer.setName("testMethod");

		return serializer;
	}
}
