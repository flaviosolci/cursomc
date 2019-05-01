package br.com.cursomc.dto.cliente;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.cursomc.services.validation.ClienteInsert;
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
@ClienteInsert
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
	@NotEmpty(message = "CPF ou CNPJ do Cliente é obrigatório")
	private String cpfOuCnpj;
	/** Tipo do cliente. */
	@Min(value = 1, message = "Tipo do Cliente é obrigatório")
	private Integer tipo;

	// =============================
	// == ENDEREÇO
	// =============================
	/** Nome da rua */
	@NotEmpty(message = "Logradouro do Cliente é obrigatório")
	private String logradouro;
	/** Numero da residência */
	@NotEmpty(message = "Número da residência do Cliente é obrigatório")
	private String numero;
	/** Complemento, se houver */
	private String complemento;
	/** Bairro */
	@NotEmpty(message = "Bairro do Cliente é obrigatório")
	private String bairro;
	/** CEP */
	@NotEmpty(message = "CEP do Cliente é obrigatório")
	private String cep;

	// =============================
	// == TELEFONE
	// =============================

	/** Telefone obrigatório */
	@NotEmpty(message = "Ao menos um telefone deve ser informado")
	private String telefone1;
	/** Telefone opcional */
	private String telefone2;
	/** Telefone opcional */
	private String telefone3;

	// =============================
	// == CIDADE
	// =============================

	/** Id da cidade desse cliente. Relacionado com o endereço informado */
	@Min(value = 1, message = "Cidade do Cliente é obrigatório")
	private Integer cidadeId;

}
