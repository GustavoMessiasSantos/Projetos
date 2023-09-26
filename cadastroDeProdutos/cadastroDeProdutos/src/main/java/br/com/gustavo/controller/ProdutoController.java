package br.com.gustavo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.gustavo.model.Produto;

@Controller
@ResponseBody
public class ProdutoController {
	
	/*@GetMapping("/olamundo")
	public String olaMundo() {
		return "Olá Mundo";
	}*/
	
	@GetMapping("/listarprodutos")
	public List<Produto> listarProdutos() {
		Produto p1 = new Produto(10, 2.99, "Sabonete");
		Produto p2 = new Produto(8, 6.99, "Pasta de dente");
		Produto p3 = new Produto(6, 8.99, "Shampoo");
		
		return Arrays.asList(p1, p2, p3);
	}

}
