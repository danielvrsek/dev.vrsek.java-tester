package vrsek.javatester.core.serializers.source;

import vrsek.javatester.core.serializers.source.model.AccessModifier;

public class JavaFieldSourceSerializer implements IMemberSourceSerializer {
	private AccessModifier accessModifier;
	private String typeName;
	private String name;

	public JavaFieldSourceSerializer() {
		initializeDefaultValues();
	}

	private void initializeDefaultValues(){
		accessModifier = AccessModifier.PUBLIC;
	}

	@Override
	public void setAccessModifier(AccessModifier accessModifier) {

		this.accessModifier = accessModifier;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setTypeName(String typeName) {

		this.typeName = typeName;
	}

	@Override
	public String serialize() {
		return String.format("%s %s %s;", accessModifier, typeName, name);
	}
}
