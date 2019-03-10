package dev.vrsek.javatester.core.serializers.source;

import dev.vrsek.javatester.core.serializers.ISerializer;
import dev.vrsek.javatester.core.serializers.source.model.AccessModifier;

public interface ISourceSerializer extends ISerializer<String> {
	void setAccessModifier(AccessModifier accessModifier);
}
