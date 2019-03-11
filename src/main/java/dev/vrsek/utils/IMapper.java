package dev.vrsek.utils;

public interface IMapper<TFrom, TTo> {
	TTo map(TFrom input);
}
