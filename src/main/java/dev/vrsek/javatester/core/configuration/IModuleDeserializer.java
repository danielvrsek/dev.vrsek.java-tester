package dev.vrsek.javatester.core.configuration;

import com.google.gson.JsonDeserializer;
import dev.vrsek.javatester.core.configuration.model.Module;

public interface IModuleDeserializer<T extends Module> extends JsonDeserializer<T> {
	String getModuleIdentifier();
}
