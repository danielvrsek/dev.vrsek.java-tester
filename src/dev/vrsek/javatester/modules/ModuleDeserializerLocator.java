package dev.vrsek.javatester.modules;

import dev.vrsek.javatester.core.configuration.IModuleDeserializer;
import dev.vrsek.javatester.modules.classfield.ClassFieldEvaluationModule;
import dev.vrsek.javatester.modules.classfield.configuration.ClassFieldModuleDeserializer;
import dev.vrsek.javatester.modules.external.ExternalEvaluatorModule;
import dev.vrsek.javatester.modules.external.configuration.ExternalEvaluationModuleDeserializer;
import dev.vrsek.javatester.modules.inheritance.InheritanceEvaluationModule;
import dev.vrsek.javatester.modules.inheritance.configuration.InheritanceModuleDeserializer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ModuleDeserializerLocator implements IModuleDeserializerLocator  {
	private Map<String, IModuleDeserializer> moduleDeserializerMap;

	private void initialize() {
		moduleDeserializerMap = new HashMap<>();
		moduleDeserializerMap.put(ClassFieldEvaluationModule.MODULE_IDENTIFIER, new ClassFieldModuleDeserializer());
		moduleDeserializerMap.put(ExternalEvaluatorModule.MODULE_IDETIFIER, new ExternalEvaluationModuleDeserializer());
		moduleDeserializerMap.put(InheritanceEvaluationModule.MODULE_IDENTIFIER, new InheritanceModuleDeserializer());
	}

	@Override
	public IModuleDeserializer find(String identifier) {
		if (moduleDeserializerMap == null) {
			initialize();
		}

		return moduleDeserializerMap.get(identifier);
	}

	@Override
	public Collection<IModuleDeserializer> findAll() {
		if (moduleDeserializerMap == null) {
			initialize();
		}

		return moduleDeserializerMap.values();
	}
}
