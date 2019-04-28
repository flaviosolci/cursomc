package br.com.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Classe modelo para Categorias. Tabela: CATEGORIA
 *
 * @author Flavio Solci
 *
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Categoria implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID da categoria */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** Nome da Categoria */
	@NonNull
	private String nome;

	/** Lista de produtos. Uma categoria pode ter varios produtos associados */
	@JsonManagedReference
	@ManyToMany(mappedBy = "categorias")
	private List<Produto> produtos = new ArrayList<>();

}
