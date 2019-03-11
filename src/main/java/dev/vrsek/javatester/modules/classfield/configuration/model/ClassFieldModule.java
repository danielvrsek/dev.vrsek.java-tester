package dev.vrsek.javatester.modules.classfield.configuration.model;

import java.util.Collection;

public class ClassFieldModule {
	private String name;
	private String description;

	private Collection<FieldDefinition> fields;

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Collection<FieldDefinition> getFields() {
		return fields;
	}
}
