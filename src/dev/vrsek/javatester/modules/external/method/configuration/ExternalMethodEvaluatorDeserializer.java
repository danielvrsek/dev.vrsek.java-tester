package dev.vrsek.javatester.modules.external.method.configuration;

import com.google.gson.*;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodDefinition;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodEvaluation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// TODO: submodule deserializer
public class ExternalMethodEvaluatorDeserializer implements JsonDeserializer<ExternalMethodEvaluation> {
	@Override
	public ExternalMethodEvaluation deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonArray jArray = jsonElement.getAsJsonArray();
		List<ExternalMethodDefinition> definitions = new ArrayList<>();
		ExternalMethodEvaluation externalMethodEvaluation = new ExternalMethodEvaluation();

		for (JsonElement element : jArray) {
			definitions.add(jsonDeserializationContext.deserialize(element, ExternalMethodDefinition.class));
		}

		externalMethodEvaluation.setExternalMethodDefinitions(definitions);

		return externalMethodEvaluation;
	}
}
