package dev.vrsek.javatester.modules.classfield.configuration.model;

import dev.vrsek.javatester.core.configuration.classtest.model.EvaluationModel;

import java.util.List;

public class MethodCallEvaluation extends EvaluationModel {
	// Type where the evaluated method/s are implemented
	private String type;
	// Method definitions to be
	private List<MethodDefinition> methods;

	public String getType() {
		return type;
	}

	public List<MethodDefinition> getMethods() {
		return methods;
	}
}
