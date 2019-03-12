package dev.vrsek.localization.json;

import com.google.gson.*;
import dev.vrsek.utils.collections.Collections;
import dev.vrsek.localization.Locale;
import dev.vrsek.localization.LocalizationPackage;
import dev.vrsek.localization.LocalizationString;

import java.lang.reflect.Type;

public class LocalizationPackageJsonDeserializer implements JsonDeserializer<LocalizationPackage> {
	private final Locale locale;

	public LocalizationPackageJsonDeserializer(Locale locale) {

		this.locale = locale;
	}

	@Override
	public LocalizationPackage deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
		JsonObject jObject = jsonElement.getAsJsonObject();

		LocalizationPackage localizationPackage = new LocalizationPackage(locale);

		var groupsByResourceName = Collections.groupBy(jObject.keySet(), LocalizationString::getResourceName);

		for (var nameGroup : groupsByResourceName) {
			LocalizationString localizationString = new LocalizationString(nameGroup.getKey());
			var availableLocales = Collections.toHashTable(nameGroup, LocalizationString::getLocaleId);

			for (var localeId : availableLocales.keySet()) {
				Locale locale = new Locale(localeId);
				String resourceName = availableLocales.get(localeId);
				String resourceString = jObject.get(resourceName).getAsString();

				localizationString.put(locale, resourceString);
			}

			localizationPackage.addLocalizationString(localizationString);
		}

		return localizationPackage;
	}
}
