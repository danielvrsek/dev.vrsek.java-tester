package dev.vrsek.javatester.modules.classfield;

import dev.vrsek.javatester.modules.*;
import dev.vrsek.javatester.modules.classfield.configuration.model.ClassFieldModule;
import dev.vrsek.javatester.modules.classfield.configuration.model.ClassFieldModuleCollection;
import dev.vrsek.javatester.modules.classfield.configuration.model.EvaluationMethod;
import dev.vrsek.javatester.modules.classfield.configuration.model.FieldDefinition;
import dev.vrsek.source.builders.AccessModifierToModifierMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
	public void evaluate(ClassFieldModuleCollection configurationCollection, RootEvaluationContext context) {
		EvaluationContext childContext = new EvaluationContext(MODULE_IDENTIFIER);
		context.addChildContext(childContext);

		for (var configuration : configurationCollection.getClassFieldModules()) {
			evaluate(context.getEvaluatedClass(), configuration, context);
		}
	}

	private void evaluate(Class type, ClassFieldModule configuration, IReadOnlyEvaluationContext context) {
		for (var fieldConfig : configuration.getFields()) {
			EvaluationContext childContext = new EvaluationContext(fieldConfig.getName(), context);

			// Evaluation method == EXISTS
			if (fieldConfig.getEvaluation().equals(EvaluationMethod.EXISTS)) {
				// Get field
				Field field = getField(type, fieldConfig.getName());

				// Add error if field does not exist and skip to the next element
				if (field == null) {
					childContext.addEvaluationError(
							new EvaluationError("Field %s does not exist.", fieldConfig.getName())
					);
					continue;
				}

				evaluate(field, fieldConfig, childContext);
			}
		}
	}

	private Field getField(Class type, String name) {
		try {
			return type.getDeclaredField(name);
		} catch (NoSuchFieldException e) {
			return null;
		}
	}

	private void evaluate(Field field, FieldDefinition configuration, IReadOnlyEvaluationContext context) {
		// Evaluate access modifier
		Integer modifiers = field.getModifiers() & Modifier.fieldModifiers();
		Integer expectedModifier = accessModifierToModifierMapper.map(configuration.getAccessModifier());
		IFieldEvaluator accessModifierEvaluator = new AccessModifierFieldEvaluator(modifiers, expectedModifier);
		accessModifierEvaluator.evaluate(context);

		// Evaluate type
		IFieldEvaluator typeEvaluator = new FieldTypeEvaluator(field.getType(), configuration.getType());
		typeEvaluator.evaluate(context);
	}
}
