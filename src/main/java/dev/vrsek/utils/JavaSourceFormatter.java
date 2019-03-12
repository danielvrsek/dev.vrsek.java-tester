package dev.vrsek.utils;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;

public class JavaSourceFormatter implements ISourceFormatter {
	private final Formatter formatter;

	public JavaSourceFormatter() {
		this.formatter = new Formatter();
	}

	@Override
	public String format(String input) {
		String formattedSource = null;
		try {
			formattedSource = formatter.formatSource(input);
		} catch (FormatterException e) {
			e.printStackTrace();
		}

		return formattedSource;
	}
}
