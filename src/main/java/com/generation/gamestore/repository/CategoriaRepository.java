package com.generation.gamestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.gamestore.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	  public List<Categoria> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}




/*

A classe Categoria, que é a Entidade que será mapeada em nosso Banco de dados 
(A Classe Categoria foi quem gerou a nossa tabela tb_categorias)


Long representa a nossa Chave Primária (Primary Key). 
Atributo que recebeu a anotação @Id na nossa Classe Categoria 
(o Atributo também se chama id em nossa Classe Categoria).


public: Define o modificador de acesso do método como público, o que significa que ele pode ser acessado por outras classes.
List<Categoria>: Define o tipo de retorno do método como uma lista de objetos do tipo Categoria. Isso indica que o método retorna uma lista contendo todas as categorias que correspondem aos critérios da consulta.
findAllByNomeContainingIgnoreCase: É o nome do método. Nesse caso, indica que queremos buscar todas as categorias cujo nome contenha uma determinada sequência de caracteres (ignorando maiúsculas e minúsculas).
@Param("nome"): É uma anotação usada para associar um parâmetro do método a um nome específico. Nesse caso, o parâmetro nome é mapeado para o campo :nome na consulta.
String nome: É o parâmetro do método que representa a sequência de caracteres que será usada para buscar categorias por nome.

*/