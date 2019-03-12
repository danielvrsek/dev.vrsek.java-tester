package dev.vrsek.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Collections {
	public static <TKey, TValue> Collection<IGrouping<TKey, TValue>> groupBy(Collection<TValue> collection,
																			Function<TValue, TKey> keySelector) {
		Hashtable<TKey, Grouping<TKey, TValue>> table = new Hashtable<>();

		for (TValue val : collection) {
			TKey key = keySelector.apply(val);

			if (!table.containsKey(key)) {
				table.put(key, new Grouping<>(key));
			}

			table.get(key).add(val);
		}

		return new ArrayList<>(table.values());
	}

	public static <TKey, TValue> Hashtable<TKey, TValue> toHashTable(Collection<TValue> collection,
																	 Function<TValue, TKey> keySelector) {
		Hashtable<TKey, TValue> table = new Hashtable<>();

		for (TValue val : collection) {
			TKey key = keySelector.apply(val);
			table.put(key, val);
		}

		return table;
	}
}
