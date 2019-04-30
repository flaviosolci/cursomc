package br.com.cursomc.services.exception;

/**
 * Erro para ser usado quando ao tentar existe uma Foreign Key entre tabelas,
 * porém se tenta deletar apenas um lado. Ex.: deletar um categoria que tenha
 * produtos
 *
 * @author Flavio Solci
 *
 */
public class DataIntegrityException extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * Erro com mensagem
	 *
	 * @param msg Mensagem de erro
	 */
	public DataIntegrityException(final String msg) {
		super(msg);
	}

	/**
	 * Erro com mensagem e cause
	 *
	 * @param message Mensagem
	 * @param cause   Causa da exceção
	 */
	public DataIntegrityException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
