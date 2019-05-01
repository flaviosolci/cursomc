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
public class ClienteNewDTO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	// =============================
	// == CLIENTE
	// =============================

	/** Nome do cliente */
	@NotEmpty(message = "Nome do Cliente é obrigatório")
	@Size(min = 5, max = 120, message = "Nome do Cliente deve ter entre 5 e 120 caracteres")
	private String nome;
	/** Email do Cliente */
	@NotEmpty(message = "E-mail do Cliente é obrigatório")
	@Email(message = "E-mail é inválido")
	private String email;
	/** Dependendo do tipo do cliente pode ter CPF ou CNPJ */
	private String cpfOuCnpj;
	/** Tipo do cliente. */
	private Integer tipo;

	// =============================
	// == ENDEREÇO
	// =============================
	/** Nome da rua */
	private String logradouro;
	/** Numero da residência */
	private String numero;
	/** Complemento, se houver */
	private String complemento;
	/** Bairro */
	private String bairro;
	/** CEP */
	private String cep;

	// =============================
	// == TELEFONE
	// =============================

	/** Telefone obrigatório */
	private String telefone1;
	/** Telefone opcional */
	private String telefone2;
	/** Telefone opcional */
	private String telefone3;

	// =============================
	// == CIDADE
	// =============================

	/** Id da cidade desse cliente. Relacionado com o endereço informado */
	private Integer cidadeId;

	/**
	 * Construtor com parâmetro do objeto original
	 *
	 * @param cliente Cliente para ser transformada em DTO
	 */
	public ClienteNewDTO(final Cliente cliente) {
		nome = cliente.getNome();
		email = cliente.getEmail();

	}

}
