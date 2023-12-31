package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Produto;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.ProdutoService;

@RestController
@CrossOrigin
@RequestMapping("/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos(@RequestParam String criterioOrdenacao) {

		if (criterioOrdenacao.equals("denominacao")) {
			List<Produto> produtos = produtoService.listarProdutos();
			return new ResponseEntity<>(produtos, HttpStatus.OK);
		}

		List<Produto> produtos = produtoService.listarProdutosPorQuantitativo();
		return new ResponseEntity<>(produtos, HttpStatus.OK);

	}

	@GetMapping("listar-produtos")
	public ResponseEntity<List<Produto>> listarTodosProdutos() {
		List<Produto> produtos = produtoService.listarProdutos();
		return new ResponseEntity<>(produtos, HttpStatus.OK);
	}

	@PostMapping("cadastrar-produto")
	public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto) {

		try {
			produtoService.salvarProduto(produto);
			return ResponseEntity.ok("Produto cadastrado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
