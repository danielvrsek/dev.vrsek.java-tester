package dev.vrsek.javatester.modules;

import java.util.ArrayList;
import java.util.List;

public class EvaluationContext implements IReadOnlyEvaluationContext {
	private final String identifier;
	private final List<IReadOnlyEvaluationContext> childContexts;
	private final List<EvaluationError> evaluationErrors;
	private IReadOnlyEvaluationContext parentContext;

	public EvaluationContext(String identifier, IReadOnlyEvaluationContext parentContext) {
		this(identifier);
		parentContext.addChildContext(this);
	}

	public EvaluationContext(String identifier) {
		this.identifier = identifier;

		evaluationErrors = new ArrayList<>();
		childContexts = new ArrayList<>();
	}

	public String getIdentifier() {
		return identifier;
	}

	public IReadOnlyEvaluationContext getParentContext() {
		return parentContext;
	}

	public void addChildContext(EvaluationContext context) {
		childContexts.add(context);
		context.parentContext = this;
	}

	public List<IReadOnlyEvaluationContext> getChildContexts() {
		return childContexts;
	}

	public void addEvaluationError(EvaluationError error) {
		evaluationErrors.add(error);
	}

	public List<EvaluationError> getEvaluationErrors() {
		return evaluationErrors;
	}
}
