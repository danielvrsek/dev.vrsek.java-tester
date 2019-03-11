package dev.vrsek.javatester.core.configuration.classtest.model;

import com.google.gson.JsonObject;
import dev.vrsek.javatester.core.configuration.Configuration;
import dev.vrsek.utils.Pair;

import java.util.List;

public class ClassTestConfiguration extends Configuration {
	// Method of the class to be evaluated
	private String evaluatedMethodName;

	// List of modules that are going to evaluate correctness of the method
	private List<Pair<String, JsonObject>> modules;
}
