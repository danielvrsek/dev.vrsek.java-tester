package dev.vrsek.javatester.modules;

// TODO: find more suitable name
public class RootEvaluationContext extends EvaluationContext {
	private Class evaluatedClass;

	public RootEvaluationContext(String name) {
		super(name);
	}

	public Class getEvaluatedClass() {
		return evaluatedClass;
	}

	protected void setEvaluatedClass(Class evaluatedClass) {
		this.evaluatedClass = evaluatedClass;
	}
}
