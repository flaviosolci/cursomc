package br.com.cursomc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Classe modelo para Categorias.
 * Tabela: CATEGORIA
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Categoria {

	/** ID da categoria */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** Nome da Categoria */
	@NonNull
	private String nome;

}
