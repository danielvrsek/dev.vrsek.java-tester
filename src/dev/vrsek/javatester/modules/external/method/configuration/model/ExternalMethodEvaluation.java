package dev.vrsek.javatester.modules.external.method.configuration.model;

import dev.vrsek.javatester.modules.external.configuration.model.SubModule;

import java.util.Collection;

public class ExternalMethodEvaluation extends SubModule {
	private Collection<ExternalMethodDefinition> externalMethodDefinitions;

	public Collection<ExternalMethodDefinition> getExternalMethodDefinitions() {
		return externalMethodDefinitions;
	}

	public void setExternalMethodDefinitions(Collection<ExternalMethodDefinition> externalMethodDefinitions) {
		this.externalMethodDefinitions = externalMethodDefinitions;
	}}
