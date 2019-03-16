package dev.vrsek.javatester.modules.external.method.configuration.model;

import dev.vrsek.javatester.modules.external.configuration.model.SubModule;

public class ExternalMethodEvaluation extends SubModule {
	private String evaluationMethodName;
	private String resultOfMethod;

	public String getEvaluationMethodName() {
		return evaluationMethodName;
	}

	public void setEvaluationMethodName(String evaluationMethodName) {
		this.evaluationMethodName = evaluationMethodName;
	}

	public String getResultOfMethod() {
		return resultOfMethod;
	}

	public void setResultOfMethod(String resultOfMethod) {
		this.resultOfMethod = resultOfMethod;
	}
}
