package br.com.rafaelson.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelson.model.Produto;
import br.com.rafaelson.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	/*
	 * @GetMapping("/olamundo") public String olaMundo() { return "Olá Mundo"; }
	 */

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listarProdutos() {

		/*
		 * Produto p1 = new Produto("Celular Samsung", 35, 1299.99); Produto p2 = new
		 * Produto("Cafeteira Arno", 10, 199.99); Produto p3 = new
		 * Produto("Mouse sem Fio", 150, 9.95);
		 * 
		 * return Arrays.asList(p1, p2, p3);
		 */

		List<Produto> produtos = produtoRepository.findAll();
		return produtos;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		return produtoRepository.findById(id).map(objetoGravado -> ResponseEntity.ok().body(objetoGravado))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
		return produtoRepository.findById(id).map(objetoGravado -> {
			objetoGravado.setNomeProduto(produto.getNomeProduto());
			objetoGravado.setQuantidade(produto.getQuantidade());
			objetoGravado.setPreco(produto.getPreco());
			Produto produtoAtualizado = produtoRepository.save(objetoGravado);
			return ResponseEntity.ok().body(produtoAtualizado);
		}).orElse(ResponseEntity.notFound().build());
	}
}
