package dev.vrsek.javatester.core.configuration.classtest.model;

import com.google.gson.JsonObject;
import dev.vrsek.utils.Pair;

public class EvaluationModel extends Pair<String, JsonObject> {

	public EvaluationModel(String key, JsonObject value) {
		super(key, value);
	}
}
