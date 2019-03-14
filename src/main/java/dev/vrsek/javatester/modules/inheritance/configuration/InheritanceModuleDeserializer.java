package dev.vrsek.javatester.modules.inheritance.configuration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.vrsek.javatester.core.configuration.IModuleDeserializer;
import dev.vrsek.javatester.modules.inheritance.InheritanceEvaluationModule;
import dev.vrsek.javatester.modules.inheritance.configuration.model.InheritanceModule;

import java.lang.reflect.Type;

public class InheritanceModuleDeserializer implements IModuleDeserializer<InheritanceModule> {
	@Override
	public String getModuleIdentifier() {
		return InheritanceEvaluationModule.MODULE_IDENTIFIER;
	}

	@Override
	public InheritanceModule deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return jsonDeserializationContext.deserialize(jsonElement, InheritanceModule.class);
	}
}
