package br.com.cursomc.domain.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 *
 * Classe modela das cidades
 *
 * @author Flavio Solci
 *
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -1511946562905621859L;

	/** Id da Cidade */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	/** Nome da cidade. Ex: São Carlos */
	private String nome;
	/** Estado dessa cidade */
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	/**
	 * Construtor apenas com o id da cidade
	 *
	 * @param cidadeId Id da cidade
	 */
	public Cidade(final Integer cidadeId) {
		id = cidadeId;
	}

	/**
	 * Construtor para cidades
	 *
	 * @param nome   Nome da cidade
	 * @param estado Estado da cidade
	 */
	public Cidade(@NonNull final String nome, @NonNull final Estado estado) {
		this.nome = nome;
		this.estado = estado;
	}

}
