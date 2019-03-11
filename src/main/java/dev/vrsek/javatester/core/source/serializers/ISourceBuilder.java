package dev.vrsek.javatester.core.source.serializers;

import dev.vrsek.javatester.core.source.serializers.model.AccessModifier;
import dev.vrsek.utils.IBuilder;

public interface ISourceBuilder extends IBuilder<String> {
	void setAccessModifier(AccessModifier accessModifier);
}
