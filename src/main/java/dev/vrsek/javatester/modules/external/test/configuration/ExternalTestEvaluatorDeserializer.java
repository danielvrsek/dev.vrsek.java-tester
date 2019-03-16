package dev.vrsek.javatester.modules.external.test.configuration;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import dev.vrsek.javatester.modules.external.test.configuration.model.ExternalTestEvaluation;

import java.lang.reflect.Type;

// TODO: generic submodule deserializer
public class ExternalTestEvaluatorDeserializer implements JsonDeserializer<ExternalTestEvaluation> {
	@Override
	public ExternalTestEvaluation deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		return jsonDeserializationContext.deserialize(jsonElement, ExternalTestEvaluation.class);
	}
}
