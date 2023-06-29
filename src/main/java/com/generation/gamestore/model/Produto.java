package com.generation.gamestore.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Você deve atribuir um NOME para o Produto.")
	@Size(min = 5, max = 50, message = "A Categoria deve conter no mínimo 5 e no máximo 50 caracteres")
	private String nome;
	
	@NotBlank(message = "Você deve atribua uma DESCRIÇÃO para o Produto.")
	@Size(min = 5, max = 100)
	private String descricao;
	
	@NotNull(message = "Você deve atribuir a QUANTIDADE do Produto") // Anotação usada para verificar se um campo em geral não é nulo.
	private Integer quantidade;
	
	@NotNull(message = "Você deve atribuir o PREÇO do Produto.")
    @Positive(message = "O preço deve ser maior do que zero!")
    private BigDecimal preco;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategorias() {
		return categoria;
	}

	public void setCategorias(Categoria categorias) {
		this.categoria = categorias;
	}


}
