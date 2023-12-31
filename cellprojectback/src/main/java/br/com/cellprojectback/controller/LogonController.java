package br.com.cellprojectback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.service.UsuarioService;
import security.JwtUtil;

@CrossOrigin
@RestController
@RequestMapping("/logon")
public class LogonController {

	private final UsuarioService usuarioService;

	public LogonController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("recuperar-senha")
	public ResponseEntity<String> recuperarSenha(@RequestBody Usuario usuario) {

		try {
			usuarioService.recuperarSenha(usuario.getLogin());
			return ResponseEntity.ok("E-mail de recuperação de senha enviado com sucesso.");

		} catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@PostMapping("/realizar-login")
	public ResponseEntity<String> realizarLogin(@RequestBody Usuario usuario) {

		try {
			usuarioService.realizarLogin(usuario.getLogin(), usuario.getSenha());
			return ResponseEntity.ok(JwtUtil.generateToken(usuario.getLogin()));
		}

		catch (Exception e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
