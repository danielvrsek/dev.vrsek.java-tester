package dev.vrsek.javatester.modules.methodcall.configuration.model;

import dev.vrsek.javatester.core.configuration.model.Module;

import java.util.ArrayList;
import java.util.Collection;

public class MethodCallModuleCollection extends Module {
	private final Collection<MethodCallModule> methodCallModules;

	public MethodCallModuleCollection() {
		this.methodCallModules = new ArrayList<>();
	}

	public Collection<MethodCallModule> getMethodCallModules() {
		return methodCallModules;
	}

	public void add(MethodCallModule methodCallModule) {
		this.methodCallModules.add(methodCallModule);
	}
}
