package dev.vrsek.javatester.core.configuration.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dev.vrsek.javatester.core.configuration.IConfigurationDeserializer;
import dev.vrsek.javatester.core.configuration.ModuleCollectionDeserializer;
import dev.vrsek.javatester.modules.ReflectionModuleDeserializerLocator;
import dev.vrsek.utils.Pair;

import java.lang.reflect.Type;

public class ClassTestConfigurationDeserializer implements IConfigurationDeserializer<ClassTestConfiguration> {

	@Override
	public ClassTestConfiguration deserialize(String input) {
		GsonBuilder gsonBuilder = new GsonBuilder();

		Type pairType = new TypeToken<Pair<String, Module>>() {}.getType();
		gsonBuilder.registerTypeAdapter(pairType, new ModuleCollectionDeserializer(new ReflectionModuleDeserializerLocator()));
		Gson gson = gsonBuilder.create();

		return gson.fromJson(input, ClassTestConfiguration.class);
	}
}