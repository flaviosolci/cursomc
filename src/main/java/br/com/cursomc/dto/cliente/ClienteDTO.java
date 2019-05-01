package br.com.cursomc.dto.cliente;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.cursomc.domain.cliente.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * DTO para clientes
 *
 * @author Flavio Solci
 *
 */
@NoArgsConstructor
@Data
public class ClienteDTO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do Cliente */
	private Integer id;
	/** Nome do cliente */
	@NotEmpty(message = "Nome do Cliente é obrigatório")
	@Size(min = 5, max = 120, message = "Nome do Cliente deve ter entre 5 e 120 caracteres")
	private String nome;
	/** Email do Cliente */
	@NotEmpty(message = "E-mail do Cliente é obrigatório")
	@Email(message = "E-mail é inválido")
	private String email;

	/**
	 * Construtor com parâmetro do objeto original
	 *
	 * @param cliente Cliente para ser transformada em DTO
	 */
	public ClienteDTO(final Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();

	}

}
