package dev.vrsek.source.builders;

public interface IClassSourceBuilder extends ISourceBuilder {
	void setClassName(String className);

	void setPackageName(String packageName);

	void addImports(String... imports);

	void addMembers(IMemberSourceBuilder... members);
}
