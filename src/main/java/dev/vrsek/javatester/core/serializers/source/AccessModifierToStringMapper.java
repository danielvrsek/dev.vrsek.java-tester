package dev.vrsek.javatester.core.serializers.source;

import dev.vrsek.javatester.core.serializers.source.model.AccessModifier;

public class AccessModifierToStringMapper implements IMapper<AccessModifier, String> {
	@Override
	public String map(AccessModifier input) {
		switch (input) {
			case PRIVATE:
				return "private";
			case PROTECTED:
				return "protected";
			case PUBLIC:
				return "public";
			default:
				return null;
		}
	}
}
