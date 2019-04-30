package br.com.cursomc.resources.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cursomc.services.exception.DataIntegrityException;
import br.com.cursomc.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	/**
	 * Handler para exceções do tipo {@link ObjectNotFoundException}. Toda a vez que
	 * acontecer uma exceção desse tipo o Spring automaticamente vai chamar esse
	 * método e retornar um objecto {@link StandardError}
	 *
	 * @param exception Exceção {@link ObjectNotFoundException}
	 * @param request   Http request (obrigatário para Spring)
	 * @return Mensagem de erro
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(final ObjectNotFoundException exception,
			final HttpServletRequest request) {
		final StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getLocalizedMessage(),
				LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	/**
	 * Handler para exceções do tipo {@link DataIntegrityException}. Toda a vez que
	 * acontecer uma exceção desse tipo o Spring automaticamente vai chamar esse
	 * método e retornar um objecto {@link StandardError}
	 *
	 * @param exception Exceção {@link DataIntegrityException}
	 * @param request   Http request (obrigatário para Spring)
	 * @return Mensagem de erro
	 */
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(final DataIntegrityException exception,
			final HttpServletRequest request) {
		final StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getLocalizedMessage(),
				LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
