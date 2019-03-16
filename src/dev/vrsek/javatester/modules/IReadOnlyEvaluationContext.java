package dev.vrsek.javatester.modules;

import java.util.List;

public interface IReadOnlyEvaluationContext {
	String getIdentifier();

	void addChildContext(EvaluationContext context);

	IReadOnlyEvaluationContext getParentContext();

	List<IReadOnlyEvaluationContext> getChildContexts();

	List<EvaluationError> getEvaluationErrors();
}
