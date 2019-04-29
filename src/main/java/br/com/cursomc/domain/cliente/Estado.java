package br.com.cursomc.domain.cliente;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * Classe modelo de Estados (UF)
 *
 * @author Flavio Solci
 *
 */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Id do estado */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	/** Nome do estado. Ex: São Paulo */
	@NonNull
	private String nome;

}
