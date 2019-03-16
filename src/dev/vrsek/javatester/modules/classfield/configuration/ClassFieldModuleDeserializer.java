package dev.vrsek.javatester.modules.classfield.configuration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.vrsek.javatester.core.configuration.IModuleDeserializer;
import dev.vrsek.javatester.modules.classfield.ClassFieldEvaluationModule;
import dev.vrsek.javatester.modules.classfield.configuration.model.ClassFieldModule;
import dev.vrsek.javatester.modules.classfield.configuration.model.ClassFieldModuleCollection;

import java.lang.reflect.Type;

public class ClassFieldModuleDeserializer implements IModuleDeserializer<ClassFieldModuleCollection> {
	@Override
	public String getModuleIdentifier() {
		return ClassFieldEvaluationModule.MODULE_IDENTIFIER;
	}

	@Override
	public ClassFieldModuleCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		ClassFieldModuleCollection collection = new ClassFieldModuleCollection();

		for (var e : jsonElement.getAsJsonArray()) {
			collection.add(jsonDeserializationContext.deserialize(e, ClassFieldModule.class));
		}

		return collection;
	}
}
