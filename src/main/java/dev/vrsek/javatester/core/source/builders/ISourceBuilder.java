package dev.vrsek.javatester.core.source.builders;

import dev.vrsek.javatester.core.source.builders.model.AccessModifier;
import dev.vrsek.utils.IBuilder;

public interface ISourceBuilder extends IBuilder<String> {
	void setAccessModifier(AccessModifier accessModifier);
}
