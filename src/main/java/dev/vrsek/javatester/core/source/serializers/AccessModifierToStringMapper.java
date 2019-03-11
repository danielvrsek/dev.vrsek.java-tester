package dev.vrsek.javatester.core.source.serializers;

import dev.vrsek.javatester.core.source.serializers.model.AccessModifier;
import dev.vrsek.utils.IMapper;

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
