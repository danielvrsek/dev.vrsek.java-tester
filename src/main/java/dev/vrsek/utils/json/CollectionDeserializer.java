package dev.vrsek.utils.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionDeserializer<T> implements JsonDeserializer<Collection<T>> {

	private final JsonDeserializer<T> elementDeserializer;

	public CollectionDeserializer(JsonDeserializer<T> elementDeserializer) {
		this.elementDeserializer = elementDeserializer;
	}

	@Override
	public Collection<T> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonArray jArray = jsonElement.getAsJsonArray();
		List<T> elements = new ArrayList<>();

		for (JsonElement element : jArray) {
			T elementVal = elementDeserializer.deserialize(element, type, jsonDeserializationContext);
			elements.add(elementVal);
		}

		return elements;
	}
}
