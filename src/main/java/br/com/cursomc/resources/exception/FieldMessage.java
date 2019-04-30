package br.com.cursomc.resources.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que contém o erro de cada campo na validação
 *
 * @author Flavio Solci
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Nome do campo com erro */
	private String fieldName;

	/** Mensagem de erro relacionada ao campo */
	private String message;

}
