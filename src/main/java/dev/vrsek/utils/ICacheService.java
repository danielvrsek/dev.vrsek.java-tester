package dev.vrsek.utils;

public interface ICacheService<TValue> {
	void add(String key, TValue value);

	TValue get(String key);
}
