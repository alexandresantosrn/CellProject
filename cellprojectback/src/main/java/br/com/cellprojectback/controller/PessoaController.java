package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.repository.PessoaRepository;

@CrossOrigin
@RestController
public class PessoaController {

	@RequestMapping("/get-pessoas")
	public List<Pessoa> getPessoas() {
		return PessoaRepository.getPessoas();
	}

	@PostMapping("/adicionar-pessoa")
	public ResponseEntity<String> adicionarPessoa(@RequestBody Pessoa pessoa) {
		pessoa.setId(1);
		PessoaRepository.addPessoa(pessoa);
		return ResponseEntity.ok("Cadastro realizado com sucesso!");
	}

}
