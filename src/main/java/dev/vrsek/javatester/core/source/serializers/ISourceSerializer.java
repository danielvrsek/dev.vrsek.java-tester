package dev.vrsek.javatester.core.source.serializers;

import dev.vrsek.javatester.core.source.serializers.model.AccessModifier;
import dev.vrsek.utils.ISerializer;

public interface ISourceSerializer extends ISerializer<String> {
	void setAccessModifier(AccessModifier accessModifier);
}
