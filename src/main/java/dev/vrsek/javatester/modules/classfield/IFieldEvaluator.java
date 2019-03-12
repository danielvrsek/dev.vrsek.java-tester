package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.IReadOnlyEvaluationContext;

public interface IFieldEvaluator {
	Boolean evaluate(IReadOnlyEvaluationContext readOnlyContext);
}
