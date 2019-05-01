package br.com.cursomc.services.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Anotação para realizar validações no cliente a ser atualizado
 *
 * @author Flavio Solci
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = ClienteUpdateValidator.class)
public @interface ClienteUpdate {

	String message() default "Error de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
