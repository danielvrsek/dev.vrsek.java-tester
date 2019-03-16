package dev.vrsek.javatester.modules.inheritance.configuration.model;

import dev.vrsek.javatester.core.configuration.model.Module;

// TODO: follow pattern of configuration model names - InheritanceEvaluation
public class InheritanceModule extends Module {
	private String inheritedType;
	private boolean recursive;

	public String getInheritedType() {
		return inheritedType;
	}

	public void setInheritedType(String inheritedType) {
		this.inheritedType = inheritedType;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}
}
