package br.com.cellprojectback.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.repository.UsuarioRepository;

@CrossOrigin
@RestController
public class UsuarioController {

	@GetMapping("/get-usuarios")
	public List<Usuario> getUsuarios() {
		return UsuarioRepository.getUsuarios();
	}

	@PostMapping("/cadastra-usuario")
	public ResponseEntity<String> adicionarUsuario(@RequestParam String email, String senha, String cpf) {

		Pessoa pessoa = PessoaRepository.getPessoabyCpf(cpf);

		if (pessoa != null) {
			Usuario user = new Usuario(2, email, senha, pessoa, new Date(), true);
			UsuarioRepository.addUsuario(user);

			return ResponseEntity.ok("Cadastro realizado com sucesso!");
		}

		return ResponseEntity.ok("Cadastro realizado com sucesso!");
	}

	@PostMapping("/recupera-senha")
	public ResponseEntity<String> recuperarSenha(@RequestParam String email) {

		if (!UsuarioRepository.hasUsuarioByEmail(email)) {
			return new ResponseEntity<>("E-mail não localizado na base de dados.", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok("E-mail de recuperação de senha enviado com sucesso.");
	}

	@PostMapping("/realiza-login")
	public ResponseEntity<String> realizarLogin(@RequestParam String email, String senha) {

		if (!UsuarioRepository.hasUsuarioByLoginSenha(email, senha)) {
			return new ResponseEntity<>("Email ou senha incorretos. Tente outra vez!", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok("Usuário autenticado com sucesso!");
	}

}
