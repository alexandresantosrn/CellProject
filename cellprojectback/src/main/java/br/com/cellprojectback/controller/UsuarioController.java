package br.com.cellprojectback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.repository.UsuarioRepository;

@CrossOrigin
@RestController
public class UsuarioController {

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
