package dev.vrsek.javatester.modules.external.method.configuration.model;

import dev.vrsek.javatester.modules.external.configuration.model.SubModule;

public class ExternalMethodEvaluation extends SubModule {
	private String name;
	private String resultOf;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResultOf() {
		return resultOf;
	}

	public void setResultOf(String resultOf) {
		this.resultOf = resultOf;
	}
}
