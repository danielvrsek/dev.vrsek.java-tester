package dev.vrsek.utils;

import java.util.Collection;

public interface ILocator<TKey, TValue> {
	TValue find(TKey identifier);

	Collection<TValue> findAll();
}
