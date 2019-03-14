package dev.vrsek.javatester.modules.external.configuration.model;

import dev.vrsek.javatester.core.configuration.model.Module;
import dev.vrsek.utils.Pair;

import java.util.Collection;

// TODO: evaluate external class with annotations
public class ExternalEvaluation extends Module {
	private String classPath;
	private String includeDirectory;
	// TODO: Create own super class for external evaluation (SubModules?)
	private Collection<Pair<String, SubModule>> subModules;

	public String getIncludeDirectory() {
		return includeDirectory;
	}

	public void setIncludeDirectory(String includeDirectory) {
		this.includeDirectory = includeDirectory;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public Collection<Pair<String, SubModule>> getSubModules() {
		return subModules;
	}

	public void setSubModules(Collection<Pair<String, SubModule>> subModules) {
		this.subModules = subModules;
	}
}
