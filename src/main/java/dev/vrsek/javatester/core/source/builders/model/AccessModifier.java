package dev.vrsek.javatester.core.source.builders.model;

import dev.vrsek.javatester.core.source.builders.annotations.Serializable;

public enum AccessModifier {
	@Serializable(text = "private")
	PRIVATE,
	@Serializable(text = "protected")
	PROTECTED,
	@Serializable(text = "public")
	PUBLIC
}
