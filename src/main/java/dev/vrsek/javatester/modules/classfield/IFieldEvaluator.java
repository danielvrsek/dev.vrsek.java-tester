package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.EvaluationError;

import java.util.Collection;

public interface IFieldEvaluator {
	Collection<EvaluationError> evaluate();
}
