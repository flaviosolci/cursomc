package br.com.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Modelo par Endereços
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do endereço */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	/** Nome da rua */
	@NonNull
	private String logradouro;
	/** Numero da residência */
	@NonNull
	private String numero;
	/** Complemento, se houver */
	private String complemento;
	/** Bairro */
	@NonNull
	private String bairro;
	/** CEP */
	@NonNull
	private String cep;
	/** Cliente dono do endereço */
	@NonNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonBackReference
	private Cliente cliente;
	/** Cidade */
	@NonNull
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

}
