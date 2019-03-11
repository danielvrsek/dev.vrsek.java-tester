package dev.vrsek.javatester.core.source.builders;

import dev.vrsek.javatester.core.source.builders.model.AccessModifier;
import dev.vrsek.utils.IMapper;
import dev.vrsek.utils.ISourceFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JavaClassSourceBuilder implements IClassSourceBuilder {
	private final ISourceFormatter sourceFormatter;
	private final IMapper<AccessModifier, String> accessModifierStringMapper;

	private AccessModifier accessModifier;
	private String className;
	private List<IMemberSourceBuilder> members;
	private String packageName;
	private List<String> imports;

	public JavaClassSourceBuilder(ISourceFormatter sourceFormatter, IMapper<AccessModifier, String> accessModifierStringMapper) {
		this.sourceFormatter = sourceFormatter;
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
	public void addMembers(IMemberSourceBuilder... members) {
		Collections.addAll(this.members, members);
	}

	@Override
	public String build() {
		StringBuilder sourceBuilder = new StringBuilder();

		sourceBuilder.append(serializePackageName());
		sourceBuilder.append(serializeImports());
		sourceBuilder.append(serializeClassSignature());
		sourceBuilder.append("{");
		sourceBuilder.append(serializeMembers());
		sourceBuilder.append("}");

		String plainSource = sourceBuilder.toString();

		return sourceFormatter.format(plainSource);
	}

	private String serializeClassSignature() {
		String accessModifierString = accessModifierStringMapper.map(accessModifier);

		return String.format("%s class %s", accessModifierString, className);
	}

	private String serializeMembers() {
		StringBuilder membersBuilder = new StringBuilder();

		for (var member : members) {
			membersBuilder.append(member.build());
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
