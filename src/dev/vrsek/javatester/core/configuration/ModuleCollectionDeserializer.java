package dev.vrsek.javatester.core.configuration;

import com.google.gson.*;
import dev.vrsek.javatester.core.configuration.model.Module;
import dev.vrsek.javatester.modules.IModuleDeserializerLocator;
import dev.vrsek.utils.Pair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModuleCollectionDeserializer implements JsonDeserializer<Collection<Pair<String, Module>>> {
	private final IModuleDeserializerLocator moduleLocator;

	public ModuleCollectionDeserializer(IModuleDeserializerLocator moduleLocator) {
		this.moduleLocator = moduleLocator;
	}

	@Override
	public Collection<Pair<String, Module>> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		List<Pair<String, Module>> modules = new ArrayList<>();
		JsonObject jObject = jsonElement.getAsJsonObject();

		for (String key : jObject.keySet()) {
			IModuleDeserializer<Module> moduleDeserializer = moduleLocator.find(key);

			if (moduleDeserializer == null) {
				throw new JsonParseException(new ModuleNotFoundException(key));
			}

			Module module = moduleDeserializer.deserialize(jObject.get(key), type, jsonDeserializationContext);
			modules.add(new Pair<>(key, module));
		}

		return modules;
	}
}
