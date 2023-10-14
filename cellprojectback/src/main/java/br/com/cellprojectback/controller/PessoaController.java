package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.PessoaService;

@CrossOrigin
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	private final PessoaService pessoaService;

	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@GetMapping
	public ResponseEntity<List<Pessoa>> listarPessoas() {
		List<Pessoa> pessoas = pessoaService.listarPessoas();
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> cadastrarPessoa(@RequestBody Pessoa pessoa) {

		try {
			pessoaService.salvarPessoa(pessoa);
			return ResponseEntity.ok("Cadastro realizado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

}
