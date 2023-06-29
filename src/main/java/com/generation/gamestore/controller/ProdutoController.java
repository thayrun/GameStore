package com.generation.gamestore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.gamestore.model.Produto;
import com.generation.gamestore.repository.ProdutoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping // Essa anotação é usada para mapear uma solicitação HTTP GET para um
				// determinado endpoint.
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(produtoResultado -> ResponseEntity.ok(produtoResultado))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		/*
		 * Esse método busca um produto com base no ID fornecido na URL e retorna a
		 * resposta apropriada: uma resposta HTTP 200 (OK) com o produto encontrado,
		 * caso exista, ou uma resposta HTTP com o status 404 (NOT_FOUND) se o produto
		 * não for encontrado.
		 */
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));

		/*
		 * Esse código define um endpoint GET que busca produtos pelo nome. Quando uma
		 * solicitação GET é feita para a rota "/nome/{nome}", o método getByNome é
		 * acionado. Ele recebe o valor fornecido na URL como parâmetro "nome" e retorna
		 * uma resposta HTTP 200 (OK) contendo a lista de produtos cujo nome contenha a
		 * sequência de caracteres especificada.
		 */
	}

	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
		/*
		 * @PostMapping: É a anotação que mapeia o método para a rota POST na rota
		 * principal ("/"). Isso significa que, ao enviar uma solicitação HTTP POST para
		 * essa rota, o método será acionado. 
		 * 
		 * public ResponseEntity<Produto>: Define o tipo de retorno do método como um objeto 
		 * ResponseEntity contendo um objeto do tipo Produto. 
		 * O ResponseEntity é usado para encapsular a resposta HTTP com o
		 * corpo da resposta e o código de status. 
		 * 
		 * post(@Valid @RequestBody Produtoproduto): É o método que lida com a solicitação POST. 
		 * Ele recebe um objeto do tipo Produto como parâmetro, enviado no corpo da solicitação através da
		 * anotação @RequestBody. A anotação @Valid é usada para indicar que o objeto
		 * deve ser validado com base nas anotações de validação definidas na classe
		 * Produto.
		 * 
		 * return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto
		 * )): Retorna uma resposta HTTP 201 (Created) com o corpo contendo o objeto
		 * Produto salvo. O método save do produtoRepository é usado para salvar o
		 * objeto Produto no banco de dados. O objeto salvo é retornado como corpo da
		 * resposta.
		 */
	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return produtoRepository.findById(produto.getId())
				.map(produtoResultado -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		/*
		 * @PutMapping: Essa anotação é responsável por mapear o método para a rota PUT.
		 * Isso significa que, ao enviar uma solicitação HTTP PUT para essa rota, o
		 * método será acionado. 
		 * 
		 * public ResponseEntity<Produto>: Define o tipo de
		 * retorno do método como um objeto ResponseEntity contendo um objeto do tipo
		 * Produto. O ResponseEntity é usado para encapsular a resposta HTTP com o corpo
		 * da resposta e o código de status. 
		 * 
		 * put(@Valid @RequestBody Produto produto):
		 * Esse é o método que lida com a solicitação PUT. Ele recebe um objeto do tipo
		 * Produto como parâmetro, enviado no corpo da solicitação por meio da
		 * anotação @RequestBody. A anotação @Valid é utilizada para indicar que o
		 * objeto deve ser validado com base nas anotações de validação definidas na
		 * classe Produto. 
		 * 
		 * produtoRepository.findById(produto.getId()): Nesse trecho, o
		 * método findById do produtoRepository é chamado para buscar o Produto
		 * correspondente ao ID fornecido no objeto Produto recebido na solicitação.
		 * Isso é feito para verificar se o Produto existe no banco de dados antes de
		 * atualizá-lo. 
		 * 
		 * .map(produtoResultado -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto))):
		 * Se o Produto for encontrado no banco de dados, o bloco .map é executado.
		 * Dentro desse bloco, é criada uma resposta HTTP 200 (OK) com o corpo contendo
		 * o objeto Produto atualizado. O método save do produtoRepository é utilizado
		 * para salvar as alterações no banco de dados.
		 * 
		 * .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()): Caso o Produto
		 * não seja encontrado no banco de dados, o bloco .orElse é executado. Ele cria
		 * uma resposta HTTP 404 (Not Found).
		 */
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);
		/*
		 * @ResponseStatus(HttpStatus.NO_CONTENT): Essa anotação indica que o código de
		 * status HTTP retornado pela requisição de exclusão será 204 (No Content), que
		 * é uma resposta de sucesso sem corpo.
		 * 
		 * @DeleteMapping("/{id}"): Essa anotação mapeia o método para a rota DELETE,
		 * onde {id} é o parâmetro do caminho que representa o ID do produto a ser
		 * excluído. Quando uma solicitação DELETE é feita para essa rota, o método é
		 * acionado. 
		 * 
		 * public void delete(@PathVariable Long id): Esse é o método que lida
		 * com a solicitação DELETE. Ele recebe o ID do produto a ser excluído como um
		 * parâmetro do caminho anotado com @PathVariable. 
		 * 
		 * Optional<Produto> produto =
		 * produtoRepository.findById(id): Nesse trecho, o método findById do
		 * produtoRepository é chamado para buscar o Produto correspondente ao ID
		 * fornecido na solicitação de exclusão. 
		 * 
		 * if (produto.isEmpty()) throw new
		 * ResponseStatusException(HttpStatus.NOT_FOUND): Se o Produto não for
		 * encontrado no banco de dados, ou seja, se o Optional estiver vazio, uma
		 * exceção do tipo ResponseStatusException é lançada com o código de status HTTP
		 * 404 (Not Found). Essa exceção informa ao cliente que o recurso a ser excluído
		 * não foi encontrado. 
		 * 
		 * produtoRepository.deleteById(id): Se o Produto for
		 * encontrado no banco de dados, o método deleteById do produtoRepository é
		 * chamado para excluir o Produto com o ID fornecido.
		 */
	}
}
