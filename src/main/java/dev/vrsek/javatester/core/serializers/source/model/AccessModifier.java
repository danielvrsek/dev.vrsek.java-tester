package dev.vrsek.javatester.core.serializers.source.model;

import dev.vrsek.javatester.core.serializers.source.annotations.Serializable;

public enum AccessModifier {
	@Serializable(text = "private")
	PRIVATE,
	@Serializable(text = "protected")
	PROTECTED,
	@Serializable(text = "public")
	PUBLIC
}
