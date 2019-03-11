package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.EvaluationContext;
import dev.vrsek.javatester.modules.EvaluationError;
import dev.vrsek.javatester.modules.EvaluationException;
import dev.vrsek.javatester.modules.IEvaluationModule;
import dev.vrsek.javatester.modules.classfield.configuration.model.ClassFieldModule;
import dev.vrsek.javatester.modules.classfield.configuration.model.ClassFieldModuleCollection;
import dev.vrsek.javatester.modules.classfield.configuration.model.EvaluationMethod;
import dev.vrsek.javatester.modules.classfield.configuration.model.FieldDefinition;
import dev.vrsek.javatester.services.AccessModifierToModifierMapper;
import dev.vrsek.utils.reflect.ClassLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ClassFieldEvaluationModule implements IEvaluationModule<ClassFieldModuleCollection> {
	public static final String MODULE_IDENTIFIER = "classfield";
	private final AccessModifierToModifierMapper accessModifierToModifierMapper;

	public ClassFieldEvaluationModule() {
		this.accessModifierToModifierMapper = new AccessModifierToModifierMapper();
	}

	@Override
	public Boolean canEvaluate(String key) {
		return key.equals(MODULE_IDENTIFIER);
	}

	@Override
	public void evaluate(ClassFieldModuleCollection configurationCollection, EvaluationContext context) throws EvaluationException {

		Class type = null;
		try {
			type = ClassLoader.load(context.getEvaluatedClassLocation());
		} catch (ClassNotFoundException e) {
			throw EvaluationException.create(
					new EvaluationError(String.format("Evaluated class '%s' was not found.", context.getEvaluatedClassLocation()))
			);
		}

		for (var configuration : configurationCollection.getClassFieldModules()) {
			evaluate(type, configuration);
		}
	}

	private void evaluate(Class type, ClassFieldModule configuration) throws EvaluationException {
		List<EvaluationError> errors = new ArrayList<>();

		for (var fieldConfig : configuration.getFields()) {
			// Evaluation method == EXISTS
			if (fieldConfig.getEvaluation().equals(EvaluationMethod.EXISTS)) {
				// Get field
				Field field = getField(type, fieldConfig.getName());

				// Add error if field does not exist and skip to the next element
				if (field == null) {
					errors.add(
							new EvaluationError("Field %s does not exist.", fieldConfig.getName())
					);
					continue;
				}

				evaluate(field, fieldConfig);
			}
		}

		if (errors.size() > 0) {
			throw EvaluationException.create(errors);
		}
	}

	private Field getField(Class type, String name) {
		try {
			return type.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			return null;
		}
	}

	private void evaluate(Field field, FieldDefinition configuration) throws EvaluationException {
		List<EvaluationError> errors = new ArrayList<>();

		// Evaluate access modifier
		Integer modifiers = field.getModifiers() & Modifier.fieldModifiers();
		Integer expectedModifier = accessModifierToModifierMapper.map(configuration.getAccessModifier());
		IFieldEvaluator accessModifierEvaluator = new AccessModifierFieldEvaluator(modifiers, expectedModifier);
		errors.addAll(accessModifierEvaluator.evaluate());

		// Evaluate type
		IFieldEvaluator typeEvaluator = new FieldTypeEvaluator(field.getType(), configuration.getType());
		errors.addAll(typeEvaluator.evaluate());

		if (errors.size() > 0) {
			throw EvaluationException.create(errors);
		}
	}
}
