package dev.vrsek.javatester.modules.methodcall.configuration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.vrsek.javatester.core.configuration.classtest.IModuleDeserializer;
import dev.vrsek.javatester.modules.methodcall.configuration.model.MethodCallModule;
import dev.vrsek.javatester.modules.methodcall.configuration.model.MethodCallModuleCollection;

import java.lang.reflect.Type;

public class MethodCallModuleDeserializer implements IModuleDeserializer<MethodCallModuleCollection> {
	public static final String MODULE_IDENTIFIER = "methodcall";

	@Override
	public String getModuleIdentifier() {
		return MODULE_IDENTIFIER;
	}

	@Override
	public MethodCallModuleCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		MethodCallModuleCollection collection = new MethodCallModuleCollection();

		for (var e : jsonElement.getAsJsonArray()) {
			collection.add(jsonDeserializationContext.deserialize(e, MethodCallModule.class));
		}

		return collection;
	}
}
