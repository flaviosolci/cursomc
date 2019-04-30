package br.com.cursomc.resources.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * Eror padrão a ser retornado em formato JSON
 *
 * @author Flavio Solci
 *
 */
@Data
@AllArgsConstructor
public class StandardError implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** HTTP status do erro. See {@link org.springframework.http.HttpStatus} */
	private Integer status;
	/** Mensagem do erro */
	private String mensagem;
	/** Data e hora do erro */
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private LocalDateTime timestamp;

}
