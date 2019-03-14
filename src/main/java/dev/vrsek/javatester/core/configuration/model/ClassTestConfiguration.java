package dev.vrsek.javatester.core.configuration.model;

import dev.vrsek.utils.Pair;

import java.util.Collection;

public class ClassTestConfiguration extends Configuration {
	// Path to the tested class source
	private String classPath;
	// Directory including compiled dependencies of the class
	private String includeDirectory;
	// Collection of modules that are going to evaluate correctness of the method
	private Collection<Pair<String, Module>> evaluationModules;

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getIncludeDirectory() {
		return includeDirectory;
	}

	public void setIncludeDirectory(String includeDirectory) {
		this.includeDirectory = includeDirectory;
	}

	public Collection<Pair<String, Module>> getModules() {
		return evaluationModules;
	}
}
