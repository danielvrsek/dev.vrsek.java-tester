package dev.vrsek.javatester.modules;

public class RootEvaluationContext extends EvaluationContext {
	private Class evaluatedClass;

	public RootEvaluationContext(String name) {
		super(name);
	}

	public Class getEvaluatedClassLocation() {
		return evaluatedClass;
	}

	protected void setEvaluatedClass(Class evaluatedClass) {
		this.evaluatedClass = evaluatedClass;
	}
}
