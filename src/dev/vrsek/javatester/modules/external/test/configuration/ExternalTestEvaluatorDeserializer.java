package dev.vrsek.javatester.modules.external.test.configuration;

import com.google.gson.*;
import dev.vrsek.javatester.modules.external.test.configuration.model.ExternalTestDefinition;
import dev.vrsek.javatester.modules.external.test.configuration.model.ExternalTestEvaluation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// TODO: generic submodule deserializer
public class ExternalTestEvaluatorDeserializer implements JsonDeserializer<ExternalTestEvaluation> {
	@Override
	public ExternalTestEvaluation deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonArray jArray = jsonElement.getAsJsonArray();
		List<ExternalTestDefinition> definitions = new ArrayList<>();
		ExternalTestEvaluation externalTestEvaluation = new ExternalTestEvaluation();

		for (JsonElement element : jArray) {
			definitions.add(jsonDeserializationContext.deserialize(element, ExternalTestDefinition.class));
		}

		externalTestEvaluation.setExternalTestDefinitions(definitions);

		return externalTestEvaluation;
	}
}
