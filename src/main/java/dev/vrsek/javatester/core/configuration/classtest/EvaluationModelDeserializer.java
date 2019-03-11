package dev.vrsek.javatester.core.configuration.classtest;

import com.google.common.collect.Iterables;
import com.google.gson.*;
import dev.vrsek.javatester.core.configuration.classtest.model.EvaluationModel;

import java.lang.reflect.Type;

public class EvaluationModelDeserializer implements JsonDeserializer<EvaluationModel> {
	@Override
	public EvaluationModel deserialize(JsonElement jsonElement,
												Type type,
												JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

		JsonObject jObject = jsonElement.getAsJsonObject();
		String key = Iterables.getOnlyElement(jObject.keySet());
		JsonObject value = jObject.get(key).getAsJsonObject();

		return new EvaluationModel(key, value);
	}
}
