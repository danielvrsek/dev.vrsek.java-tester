package dev.vrsek.javatester.modules.methodcall.configuration.model;

import java.util.List;

public class MethodCallModule {
	private String name;
	private String description;

	// Type where the evaluated method/s are implemented
	private String type;

	// Method definitions to be
	private List<MethodDefinition> methods;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}

	public List<MethodDefinition> getMethods() {
		return methods;
	}
}
