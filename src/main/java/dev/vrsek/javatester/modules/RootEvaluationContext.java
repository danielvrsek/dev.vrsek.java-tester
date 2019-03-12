package dev.vrsek.javatester.modules;

public class RootEvaluationContext extends EvaluationContext {
	private String evaluatedClassLocation;

	public RootEvaluationContext(String name) {
		super(name);
	}

	public String getEvaluatedClassLocation() {
		return evaluatedClassLocation;
	}

	protected void setEvaluatedClassLocation(String evaluatedClassLocation) {
		this.evaluatedClassLocation = evaluatedClassLocation;
	}
}
