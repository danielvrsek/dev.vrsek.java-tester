package vrsek.javatester.core.serializers.source;

import vrsek.javatester.core.serializers.ISerializer;
import vrsek.javatester.core.serializers.source.model.AccessModifier;

public interface ISourceSerializer extends ISerializer<String> {
	void setAccessModifier(AccessModifier accessModifier);
}
