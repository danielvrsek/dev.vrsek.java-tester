package dev.vrsek.javatester.modules.classfield.configuration.model;

import dev.vrsek.source.builders.model.AccessModifier;

public class FieldDefinition {
	private AccessModifier accessModifier;
	private String type;
	private String parentType;
	private String name;
	private EvaluationMethod evaluation;

	public AccessModifier getAccessModifier() {
		return accessModifier;
	}

	public String getType() {
		return type;
	}

	public String getParentType() {
		return parentType;
	}

	public String getName() {
		return name;
	}

	public EvaluationMethod getEvaluation() {
		return evaluation;
	}
}
