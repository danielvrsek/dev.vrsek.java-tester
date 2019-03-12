package dev.vrsek.javatester;

import dev.vrsek.javatester.core.source.builders.AccessModifierToStringMapper;
import dev.vrsek.javatester.core.source.builders.JavaClassSourceBuilder;
import dev.vrsek.javatester.core.source.builders.JavaMethodSourceBuilder;
import dev.vrsek.javatester.core.source.builders.model.AccessModifier;
import dev.vrsek.utils.JavaSourceFormatter;
import dev.vrsek.utils.compilers.runtime.InMemoryJavaCompiler;

public class Main {
	public static void main(String[] args) throws Exception {
		InMemoryJavaCompiler compiler = new InMemoryJavaCompiler();


		JavaClassSourceBuilder sourceBuilder = getSource();
		System.out.println(sourceBuilder.build());
		Class c = compiler.compile(sourceBuilder.getClassName(), sourceBuilder.build());
	}

	private static JavaClassSourceBuilder getSource() {
		JavaMethodSourceBuilder methodSourceBuilder = new JavaMethodSourceBuilder(new AccessModifierToStringMapper());
		methodSourceBuilder.setName("testMethod");
		methodSourceBuilder.setAccessModifier(AccessModifier.PRIVATE);
		methodSourceBuilder.setTypeName("String");
		methodSourceBuilder.setBody(new String[] {
				"return null;"
		});

		JavaClassSourceBuilder classSourceBuilder = new JavaClassSourceBuilder(new JavaSourceFormatter(), new AccessModifierToStringMapper());
		classSourceBuilder.setClassName("CompiledClass");
		classSourceBuilder.addImports("dev.vrsek.TestClass");
		classSourceBuilder.addMembers(methodSourceBuilder);

		return classSourceBuilder;
	}
}
