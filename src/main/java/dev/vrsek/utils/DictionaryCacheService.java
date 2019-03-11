package dev.vrsek.utils;

import java.util.HashMap;
import java.util.Map;

public class DictionaryCacheService implements ICacheService {
	private final Map<String, Object> dictionary;

	public DictionaryCacheService() {
		this.dictionary = new HashMap<>();
	}

	@Override
	public void add(String key, Object value) {
		dictionary.put(key, value);
	}

	@Override
	public Object get(String key) {
		return dictionary.get(key);
	}
}
