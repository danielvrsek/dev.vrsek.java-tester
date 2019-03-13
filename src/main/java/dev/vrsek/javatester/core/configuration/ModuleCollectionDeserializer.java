package dev.vrsek.javatester.core.configuration;

import com.google.common.collect.Iterables;
import com.google.gson.*;
import dev.vrsek.javatester.core.configuration.model.Module;
import dev.vrsek.javatester.modules.IModuleDeserializerLocator;
import dev.vrsek.utils.Pair;

import java.lang.reflect.Type;

public class ModuleCollectionDeserializer implements JsonDeserializer<Pair<String, Module>> {
	private final IModuleDeserializerLocator moduleLocator;

	public ModuleCollectionDeserializer(IModuleDeserializerLocator moduleLocator) {
		this.moduleLocator = moduleLocator;
	}

	@Override
	public Pair<String, Module> deserialize(JsonElement jsonElement,
														Type type,
														JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject jObject = jsonElement.getAsJsonObject();
		String key = Iterables.getOnlyElement(jObject.keySet());

		IModuleDeserializer<Module> moduleDeserializer = moduleLocator.find(key);

		if (moduleDeserializer == null) {
			throw new JsonParseException(new ModuleNotFoundException(key));
		}

		Module module = moduleDeserializer.deserialize(jObject.get(key), type, jsonDeserializationContext);

		return new Pair<>(key, module);
	}
}
