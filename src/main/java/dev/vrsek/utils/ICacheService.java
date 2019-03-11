package dev.vrsek.utils;

public interface ICacheService {
	void add(String key, Object value);

	Object get(String key);
}
