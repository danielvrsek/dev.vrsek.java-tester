package dev.vrsek.javatester.core.serializers.source;

import dev.vrsek.javatester.core.serializers.source.model.AccessModifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaClassSourceSerializer implements IClassSourceSerializer {
	private final IMapper<AccessModifier, String> accessModifierStringMapper;

	private AccessModifier accessModifier;
	private String className;
	private List<IMemberSourceSerializer> members;
	private String packageName;
	private List<String> imports;

	public JavaClassSourceSerializer(IMapper<AccessModifier, String> accessModifierStringMapper) {
		this.accessModifierStringMapper = accessModifierStringMapper;

		initializeDefaultValues();
	}

	private void initializeDefaultValues() {
		this.accessModifier = AccessModifier.PUBLIC;
		this.members = new ArrayList<>();
		this.imports = new ArrayList<>();
	}

	@Override
	public void setAccessModifier(AccessModifier accessModifier) {
		this.accessModifier = accessModifier;
	}

	@Override
	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public void addImports(String... imports) {
		Collections.addAll(this.imports, imports);
	}

	@Override
	public void addMembers(IMemberSourceSerializer... members) {
		Collections.addAll(this.members, members);
	}

	@Override
	public String serialize() {
		StringBuilder sourceBuilder = new StringBuilder();

		sourceBuilder.append(serializePackageName());
		sourceBuilder.append(serializeImports());
		sourceBuilder.append(serializeClassSignature());
		sourceBuilder.append("{");
		sourceBuilder.append(serializeMembers());
		sourceBuilder.append("}");

		return sourceBuilder.toString();
	}

	private String serializeClassSignature() {
		String accessModifierString = accessModifierStringMapper.map(accessModifier);

		return String.format("%s class %s", accessModifierString, className);
	}

	private String serializeMembers() {
		StringBuilder membersBuilder = new StringBuilder();

		for (var member : members) {
			membersBuilder.append(member.serialize());
		}

		return membersBuilder.toString();
	}

	private String serializeImports() {
		StringBuilder importsBuilder = new StringBuilder();

		for (String imp : imports) {
			importsBuilder.append("import " + imp + ";");
		}

		return importsBuilder.toString();
	}

	private String serializePackageName() {
		return "package " + packageName + ";";
	}
}
