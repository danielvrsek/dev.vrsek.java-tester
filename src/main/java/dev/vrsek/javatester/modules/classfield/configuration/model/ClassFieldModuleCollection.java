package dev.vrsek.javatester.modules.classfield.configuration.model;

import dev.vrsek.javatester.core.configuration.classtest.model.Module;

import java.util.ArrayList;
import java.util.Collection;

public class ClassFieldModuleCollection extends Module {
	private final Collection<ClassFieldModule> classFieldModules;

	public ClassFieldModuleCollection() {
		this.classFieldModules = new ArrayList<>();
	}

	public Collection<ClassFieldModule> getMethodCallModules() {
		return classFieldModules;
	}

	public void add(ClassFieldModule classFieldModule) {
		this.classFieldModules.add(classFieldModule);
	}
}
