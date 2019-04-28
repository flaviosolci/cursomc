package br.com.cursomc.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Classe modelo de produtos. Tabela: PRODUTO
 *
 * @author Flavio Solci
 *
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Produto implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ID unico do produto */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** Nome do produto */
	@NonNull
	private String nome;
	/** Preço do produto */
	@NonNull
	private BigDecimal preco;

	/** Lista de categorias. Um produto pode ter varias categorias */
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

}
