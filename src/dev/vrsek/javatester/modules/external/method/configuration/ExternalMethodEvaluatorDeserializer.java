package dev.vrsek.javatester.modules.external.method.configuration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.vrsek.javatester.modules.external.method.configuration.model.ExternalMethodEvaluation;

import java.lang.reflect.Type;

// TODO: submodule deserializer
public class ExternalMethodEvaluatorDeserializer implements JsonDeserializer<ExternalMethodEvaluation> {
	@Override
	public ExternalMethodEvaluation deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return jsonDeserializationContext.deserialize(jsonElement, ExternalMethodEvaluation.class);
	}
}
