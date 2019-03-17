package dev.vrsek.javatester.modules.external.test.configuration.model;

import dev.vrsek.javatester.modules.external.configuration.model.SubModule;

import java.util.Collection;

public class ExternalTestEvaluation extends SubModule {
	private Collection<ExternalTestDefinition> externalTestDefinitions;

	public Collection<ExternalTestDefinition> getExternalTestDefinitions() {
		return externalTestDefinitions;
	}

	public void setExternalTestDefinitions(Collection<ExternalTestDefinition> externalTestDefinitions) {
		this.externalTestDefinitions = externalTestDefinitions;
	}
}
