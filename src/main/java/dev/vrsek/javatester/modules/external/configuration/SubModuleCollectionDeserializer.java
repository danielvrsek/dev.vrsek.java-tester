package dev.vrsek.javatester.modules.external.configuration;

import com.google.gson.*;
import dev.vrsek.javatester.modules.external.configuration.model.SubModule;
import dev.vrsek.javatester.modules.external.method.configuration.ExternalMethodEvaluatorDeserializer;
import dev.vrsek.utils.Pair;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubModuleCollectionDeserializer implements JsonDeserializer<Collection<Pair<String, SubModule>>> {

	@Override
	public Collection<Pair<String, SubModule>> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		List<Pair<String, SubModule>> modules = new ArrayList<>();
		JsonObject jObject = jsonElement.getAsJsonObject();

		for (String key : jObject.keySet()) {
			// TODO: proper module search
			if (key.equals("method")) {
				var moduleDeserializer = new ExternalMethodEvaluatorDeserializer();

				SubModule module = moduleDeserializer.deserialize(jObject.get(key), type, jsonDeserializationContext);
				modules.add(new Pair<>(key, module));
			}
		}


		return modules;
	}
}
