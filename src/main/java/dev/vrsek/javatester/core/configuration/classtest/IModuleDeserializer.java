package dev.vrsek.javatester.core.configuration.classtest;

import com.google.gson.JsonDeserializer;
import dev.vrsek.javatester.core.configuration.classtest.model.Module;

public interface IModuleDeserializer<T extends Module> extends JsonDeserializer<T> {
	String getModuleIdentifier();
}
