package dev.vrsek.javatester.core.configuration.classtest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.vrsek.javatester.core.configuration.IConfigurationDeserializer;
import dev.vrsek.javatester.core.configuration.classtest.model.ClassTestConfiguration;
import dev.vrsek.javatester.core.configuration.classtest.model.EvaluationModel;

public class ClassTestConfigurationDeserializer implements IConfigurationDeserializer<ClassTestConfiguration> {

	@Override
	public ClassTestConfiguration deserialize(String input) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(EvaluationModel .class, new EvaluationModelDeserializer());
		Gson gson = gsonBuilder.create();

		return gson.fromJson(input, ClassTestConfiguration.class);
	}
}
