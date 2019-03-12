package dev.vrsek.utils;

import dev.vrsek.utils.exceptions.ValidationException;

public interface IBuilder<T> {
	T build() throws ValidationException;
}
