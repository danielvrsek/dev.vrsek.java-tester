package dev.vrsek.javatester.core.configuration.model;

import dev.vrsek.utils.Pair;

import java.util.Collection;

public class ClassTestConfiguration extends Configuration {
	// Collection of modules that are going to evaluate correctness of the method
	private Collection<Pair<String, Module>> evaluationModules;

	public Collection<Pair<String, Module>> getModules() {
		return evaluationModules;
	}
}
