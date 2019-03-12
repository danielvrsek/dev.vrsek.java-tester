package dev.vrsek.javatester;

import dev.vrsek.localization.Locale;
import dev.vrsek.localization.LocalizationManager;
import dev.vrsek.localization.json.JsonLocalizationLoader;

public class JavaTester {
	public static void main(String[] args) throws Exception {
		String json = "{\n" +
				"  \"errors\": {\n" +
				"    \"cz.fileNotFoundError\": \"Soubor nenalezen.\",\n" +
				"    \"en.fileNotFoundError\": \"File was not found.\",\n" +
				"\n" +
				"    \"cz.invalidFile\": \"Neplatny soubor\",\n" +
				"    \"en.invalidFile\": \"Invalid file\"\n" +
				"  },\n" +
				"  \"files\": {\n" +
				"    \"cz.filename\": \"Nazev souboru\",\n" +
				"    \"en.filename\": \"File name\"\n" +
				"  }\n" +
				"}";

		new JsonLocalizationLoader(json).load(Locale.CZECH);

		System.out.println(LocalizationManager.get().getString("errors.fileNotFoundError"));
		System.out.println(LocalizationManager.get().getString("errors.invalidFile"));
	}
}
