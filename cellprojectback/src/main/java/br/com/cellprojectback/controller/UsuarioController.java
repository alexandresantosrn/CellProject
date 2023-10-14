package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.listarUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> cadastrarUsuario(@RequestBody Usuario usuario) {

		try {
			usuarioService.salvarUsuario(usuario);
			return ResponseEntity.ok("Cadastro realizado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

}
