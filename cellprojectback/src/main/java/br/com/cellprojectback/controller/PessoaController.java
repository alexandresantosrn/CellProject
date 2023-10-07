package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.repository.PessoaRepository;

@CrossOrigin
@RestController
public class PessoaController {
	
	@PostMapping("/adicionar-pessoa")
	public ResponseEntity<String> addPessoa(@RequestBody Pessoa pessoa) {			
		pessoa.setId(1);
		PessoaRepository.addPessoa(pessoa);
		return ResponseEntity.ok("Usuário autenticado com sucesso!");
	}
	
	@RequestMapping("/get-pessoas")
	public List<Pessoa> getPessoas() {
		return PessoaRepository.getPessoas();
	}

}
