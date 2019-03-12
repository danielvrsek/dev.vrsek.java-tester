package dev.vrsek.utils.collections;

import java.util.ArrayList;

public class Grouping<TKey, TValue> extends ArrayList<TValue> implements IGrouping<TKey, TValue> {
	private TKey key;

	public Grouping(TKey key) {

		this.key = key;
	}

	public TKey getKey() {
		return key;
	}
}
