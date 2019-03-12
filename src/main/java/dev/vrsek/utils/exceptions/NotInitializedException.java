package dev.vrsek.utils.exceptions;

public class NotInitializedException extends Exception {
	public NotInitializedException(String identifier) {
		super("Not initiated: " + identifier);
	}
}
