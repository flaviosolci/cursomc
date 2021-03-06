package br.com.cursomc.domain.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cursomc.domain.user.Perfil;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * Modelo para clientes
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
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
	@Column(unique = true)
	private String email;
	/** Dependendo do tipo do cliente pode ter CPF ou CNPJ */
	private String cpfOuCnpj;
	/** Tipo do cliente. */
	private Integer tipo;
	/** List da endereços do cliente */
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "cliente_id")
	private List<Endereco> enderecos = new ArrayList<>();
	/** Lista de telefones */
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	@Setter(value = AccessLevel.NONE)
	@Getter(value = AccessLevel.NONE)
	private final Set<Integer> perfis = new HashSet<>();

	/**
	 * Construtor padrão. Obrigatório
	 */
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	/**
	 * Senha do cliente
	 *
	 * @see https://github.com/spring-projects/spring-security/issues/3238
	 */
	@JsonIgnore
	@ToString.Exclude
	private String senha;

	/**
	 * Construtor
	 *
	 * @param nome      Nome do cliente
	 * @param email     Email do cliente
	 * @param cpfOuCnpj Dependendo do tipo do cliente pode ter CPF ou CNPJ
	 * @param tipo      Tipo do cliente.
	 * @param senha     senha do cliente
	 */
	public Cliente(@NonNull final String nome, @NonNull final String email, @NonNull final String cpfOuCnpj,
			final TipoCliente tipo, final String senha) {
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo == null ? null : tipo.getCodigo();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
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

	/**
	 * @return Retorna todos os perfis
	 */
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(perfil -> Perfil.toPerfil(perfil)).collect(Collectors.toSet());
	}

	/**
	 * @param perfil Adiciona um novo perfil ao clinte
	 */
	public void addPerfil(final Perfil perfil) {
		perfis.add(perfil.getCodigo());
	}

}
