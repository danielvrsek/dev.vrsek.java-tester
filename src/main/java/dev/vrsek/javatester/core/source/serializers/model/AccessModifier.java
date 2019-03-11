package dev.vrsek.javatester.core.source.serializers.model;

import dev.vrsek.javatester.core.source.serializers.annotations.Serializable;

public enum AccessModifier {
	@Serializable(text = "private")
	PRIVATE,
	@Serializable(text = "protected")
	PROTECTED,
	@Serializable(text = "public")
	PUBLIC
}
