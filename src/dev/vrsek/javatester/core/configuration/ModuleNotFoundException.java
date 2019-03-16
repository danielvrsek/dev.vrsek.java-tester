package dev.vrsek.javatester.core.configuration;

public class ModuleNotFoundException extends Exception {
	public ModuleNotFoundException(String moduleIdentifier) {
		super(String.format("Could not locate module with identifier \"%s\"", moduleIdentifier));
	}

}
