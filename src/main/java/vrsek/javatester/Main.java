package vrsek.javatester;

import vrsek.javatester.core.serializers.source.AccessModifierToStringMapper;
import vrsek.javatester.core.serializers.source.IMemberSourceSerializer;
import vrsek.javatester.core.serializers.source.JavaClassSourceSerializer;
import vrsek.javatester.core.serializers.source.JavaMethodSourceSerializer;
import vrsek.javatester.core.serializers.source.model.AccessModifier;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		JavaClassSourceSerializer classSourceSerializer = new JavaClassSourceSerializer(new AccessModifierToStringMapper());

		classSourceSerializer.setAccessModifier(AccessModifier.PUBLIC);
		classSourceSerializer.setPackageName("vrsek.test");
		classSourceSerializer.setClassName("TestClass");
		classSourceSerializer.addMembers(getTestMethod());

		System.out.println(classSourceSerializer.serialize());
	}

	private static JavaMethodSourceSerializer getTestMethod() {
		JavaMethodSourceSerializer serializer = new JavaMethodSourceSerializer(new AccessModifierToStringMapper());

		serializer.setAccessModifier(AccessModifier.PRIVATE);
		serializer.setTypeName("void");
		serializer.setName("testMethod");

		return serializer;
	}
}
