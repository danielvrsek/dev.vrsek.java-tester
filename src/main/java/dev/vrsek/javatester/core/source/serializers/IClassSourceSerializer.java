package dev.vrsek.javatester.core.source.serializers;

public interface IClassSourceSerializer extends ISourceSerializer {
	void setClassName(String className);

	void setPackageName(String packageName);

	void addImports(String... imports);

	void addMembers(IMemberSourceSerializer... members);
}
