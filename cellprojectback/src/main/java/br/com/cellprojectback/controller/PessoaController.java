package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.util.PessoaUtil;

@CrossOrigin
@RestController
public class PessoaController {

	@GetMapping("/get-pessoas")
	public List<Pessoa> getPessoas() {
		return PessoaRepository.getPessoas();
	}

	@PostMapping("/adiciona-pessoa")
	public ResponseEntity<String> adicionarPessoa(@RequestBody Pessoa pessoa) {

		if (PessoaRepository.hasPessoabyCpf(pessoa.getCpf())) {
			return new ResponseEntity<>("Já existe um usuário com o cpf informado.", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		else if (PessoaRepository.hasPessoabyEmail(pessoa.getEmail())) {
			return new ResponseEntity<>("Já existe um usuário com o e-mail informado.",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		else if (!PessoaUtil.isCPFValido(pessoa.getCpf())) {
			return new ResponseEntity<>("Informe um cpf válido.", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		else {
			pessoa.setId(PessoaUtil.getNextId());
			PessoaRepository.addPessoa(pessoa);

			return ResponseEntity.ok("Cadastro realizado com sucesso!");
		}

	}

}
