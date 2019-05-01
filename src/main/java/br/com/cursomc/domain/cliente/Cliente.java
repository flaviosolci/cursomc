package br.com.cursomc.domain.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.StringUtils;

import br.com.cursomc.dto.cliente.ClienteNewDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Modelo para clientes
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do cliente */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	/** Nome do cliente */
	private String nome;
	/** Email do cliente */
	private String email;
	/** Dependendo do tipo do cliente pode ter CPF ou CNPJ */
	private String cpfOuCnpj;
	/** Tipo do cliente. */
	private Integer tipo;
	/** List da endereços do cliente */
	@OneToMany
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();
	/** Lista de telefones */
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();

	/**
	 * Construtor
	 *
	 * @param nome      Nome do cliente
	 * @param email     Email do cliente
	 * @param cpfOuCnpj Dependendo do tipo do cliente pode ter CPF ou CNPJ
	 * @param tipo      Tipo do cliente.
	 */
	public Cliente(@NonNull final String nome, @NonNull final String email, @NonNull final String cpfOuCnpj,
			@NonNull final TipoCliente tipo) {
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo == null ? null : tipo.getCodigo();
	}

	/**
	 * Construtor com parâmetro do objeto dto
	 *
	 * @param clienteNewDTO dto para ser transformado em cliente
	 */
	public Cliente(final ClienteNewDTO clienteNewDTO) {
		this(clienteNewDTO.getNome(), clienteNewDTO.getEmail(), clienteNewDTO.getCpfOuCnpj(),
				TipoCliente.toTipoCliente(clienteNewDTO.getTipo()));

		getEnderecos().add(new Endereco(clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
				clienteNewDTO.getBairro(), clienteNewDTO.getBairro(), new Cidade(clienteNewDTO.getCidadeId())));
		getTelefones().add(clienteNewDTO.getTelefone1());

		if (StringUtils.isNotEmpty(clienteNewDTO.getTelefone2())) {
			getTelefones().add(clienteNewDTO.getTelefone2());
		}

		if (StringUtils.isNotEmpty(clienteNewDTO.getTelefone2())) {
			getTelefones().add(clienteNewDTO.getTelefone3());
		}
	}

	/**
	 * Define o tipo do cliente
	 *
	 * @param tipo Tipo do cliente
	 */
	public void setTipo(final TipoCliente tipo) {
		this.tipo = tipo.getCodigo();

	}

	/**
	 * @return tipo do cliente
	 */
	public TipoCliente getTipo() {
		if (tipo == null) {
			return null;
		}
		return TipoCliente.toTipoCliente(tipo);

	}

}
