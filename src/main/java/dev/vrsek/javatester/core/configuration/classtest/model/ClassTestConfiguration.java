package dev.vrsek.javatester.core.configuration.classtest.model;

import dev.vrsek.javatester.core.configuration.Configuration;
import dev.vrsek.utils.Pair;

import java.util.Collection;

public class ClassTestConfiguration extends Configuration {
	// Method of the class to be evaluated
	private String evaluatedMethodName;

	// Collection of modules that are going to evaluate correctness of the method
	private Collection<Pair<String, Module>> evaluationModules;

	public String getEvaluatedMethodName() {
		return evaluatedMethodName;
	}

	public Collection<Pair<String, Module>> getModules() {
		return evaluationModules;
	}
}
