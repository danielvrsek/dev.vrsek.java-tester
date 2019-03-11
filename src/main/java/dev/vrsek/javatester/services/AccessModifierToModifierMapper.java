package dev.vrsek.javatester.services;

import dev.vrsek.javatester.core.source.builders.model.AccessModifier;
import dev.vrsek.utils.IMapper;

import java.lang.reflect.Modifier;

public class AccessModifierToModifierMapper implements IMapper<AccessModifier, Integer> {

	@Override
	public Integer map(AccessModifier input) {
		switch (input) {
			case PRIVATE:
				return Modifier.PRIVATE;
			case PROTECTED:
				return Modifier.PROTECTED;
			case PUBLIC:
				return Modifier.PUBLIC;
			default:
				return -1;
		}
	}
}
