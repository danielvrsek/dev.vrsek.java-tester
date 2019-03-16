package dev.vrsek.javatester.modules.external.test.configuration.model;

import dev.vrsek.javatester.modules.external.configuration.model.SubModule;

public class ExternalTestEvaluation extends SubModule {
	private String evaluationMethodName;
	private String resultOfMethod;
	private String resultType;

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

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
}
