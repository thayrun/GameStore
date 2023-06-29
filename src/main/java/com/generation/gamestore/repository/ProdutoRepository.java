package com.generation.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.generation.gamestore.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	  public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}


/*

A classe Produto, que é a Entidade que será mapeada em nosso Banco de dados 
(A Classe Produto foi quem gerou a nossa tabela tb_produtos)


Long representa a nossa Chave Primária (Primary Key). 
Atributo que recebeu a anotação @Id na nossa Classe Produto (o Atributo também se chama ID em nossa Classe Produto).




public: Define o modificador de acesso do método como público, o que significa que ele pode ser acessado por outras classes.
List<Produto>: Define o tipo de retorno do método como uma lista de objetos do tipo Produto. Isso indica que o método retorna uma lista contendo todos os produtos que correspondem aos critérios da consulta.
findAllByNomeContainingIgnoreCase: É o nome do método. Nesse caso, indica que queremos buscar todos os produtos cujo nome contenha uma determinada sequência de caracteres (ignorando maiúsculas e minúsculas).
@Param("nome"): É uma anotação usada para associar um parâmetro do método a um nome específico. Nesse caso, o parâmetro nome é mapeado para o campo :nome na consulta.
String nome: É o parâmetro do método que representa a sequência de caracteres que será usada para buscar produtos por nome.


*/