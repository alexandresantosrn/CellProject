package br.com.cellprojectback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("cadastrar-produto")
	public ResponseEntity<String> cadastrarAgendamento(@RequestBody Produto produto) {
		
		try {
			produtoService.salvarProduto(produto);
			return ResponseEntity.ok("Produto cadastrado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
}
