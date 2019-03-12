package dev.vrsek.utils.collections;

import java.util.Collection;

public interface IGrouping<TKey, TValue> extends Collection<TValue> {
	TKey getKey();
}
