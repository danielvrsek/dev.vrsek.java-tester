package dev.vrsek.javatester.core.serializers.source;

public interface IMapper<TFrom, TTo> {
	TTo map(TFrom input);
}
