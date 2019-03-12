package dev.vrsek.utils;

import java.util.HashMap;
import java.util.Map;

public class DictionaryCacheService<TValue> implements ICacheService<TValue> {
	private final Map<String, TValue> dictionary;

	public DictionaryCacheService() {
		this.dictionary = new HashMap<>();
	}

	@Override
	public void add(String key, TValue value) {
		dictionary.put(key, value);
	}

	@Override
	public TValue get(String key) {
		return dictionary.get(key);
	}
}
