package dev.vrsek.javatester.modules.external.configuration;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import dev.vrsek.javatester.core.configuration.IModuleDeserializer;
import dev.vrsek.javatester.modules.external.ExternalEvaluatorModule;
import dev.vrsek.javatester.modules.external.configuration.model.ExternalEvaluation;
import dev.vrsek.javatester.modules.external.configuration.model.SubModule;
import dev.vrsek.utils.Pair;

import java.lang.reflect.Type;
import java.util.Collection;

public class ExternalEvaluationModuleDeserializer implements IModuleDeserializer<ExternalEvaluation> {
	@Override
	public String getModuleIdentifier() {
		return ExternalEvaluatorModule.MODULE_IDETIFIER;
	}

	@Override
	public ExternalEvaluation deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		// TODO: better deserialization
		GsonBuilder builder = new GsonBuilder();
		Type t = new TypeToken<Collection<Pair<String, SubModule>>>() {}.getType();
		builder.registerTypeAdapter(t, new SubModuleCollectionDeserializer());

		return builder.create().fromJson(jsonElement, ExternalEvaluation.class);
	}
}
