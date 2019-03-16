package dev.vrsek.javatester.modules.methodcall.configuration.model;

import java.util.List;

public class MethodDefinition {
	// Name of the method
	public String name;

	// Parameters of the method signature
	public List<MethodParameter> parameters;

	// Defines how many times this method should be called
	public int callCount;

	// Defines how evaluation should behave
	public EvaluationBehavior evaluationBehavior;
}
