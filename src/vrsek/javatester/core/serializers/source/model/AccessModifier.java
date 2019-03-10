package vrsek.javatester.core.serializers.source.model;

import vrsek.javatester.core.serializers.source.annotations.Serializable;

public enum AccessModifier {
	@Serializable(text = "private")
	PRIVATE,
	@Serializable(text = "protected")
	PROTECTED,
	@Serializable(text = "public")
	PUBLIC
}
