package br.com.cursomc.resources.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * Classe para Errors de validação
 *
 * @author Flavio Solci
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationError extends StandardError {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Campos com erro */
	@Setter(value = AccessLevel.NONE)
	@JsonProperty("errors")
	private final List<FieldMessage> fieldMessages;

	/**
	 * Construtor
	 *
	 * @param status    HTTP Status
	 * @param mensagem  Mensagem do erro
	 * @param timestamp Data/Hora do erro
	 */
	public ValidationError(final Integer status, final String mensagem, final LocalDateTime timestamp) {
		super(status, mensagem, timestamp);
		fieldMessages = new ArrayList<>();
	}

	/**
	 * Adiciona um campo com erro e sua mensagem
	 *
	 * @param fieldName Campo com erros de validação
	 * @param message   Mensagem de erro
	 */
	public void addFieldWithError(final String fieldName, final String message) {
		fieldMessages.add(new FieldMessage(fieldName, message));

	}

}
