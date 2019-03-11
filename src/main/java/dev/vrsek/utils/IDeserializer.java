package dev.vrsek.utils;

public interface IDeserializer<T> {
	T deserialize(String input);
}
