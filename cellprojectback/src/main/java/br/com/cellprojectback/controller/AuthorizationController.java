package br.com.cellprojectback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.service.PermissaoService;
import security.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/authorization")
public class AuthorizationController {

	private final PermissaoService permissaoService;

	public AuthorizationController(PermissaoService permissaoService) {
		this.permissaoService = permissaoService;
	}

	@GetMapping("atendimento")
	public ResponseEntity<String> acessoAtendimento(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "");
		String username = JwtUtil.getUsernameByToken(token);

		if (permissaoService.hasPermissaoAtendimento(username)) {
			return ResponseEntity.ok("Acesso ok.");
		}

		return new ResponseEntity<>("Você não está autorizado a acessar este recurso.", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("almoxarifado")
	public ResponseEntity<String> acessoAlmoxarifado(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "");
		String username = JwtUtil.getUsernameByToken(token);

		if (permissaoService.hasPermissaoAlmoxarifado(username)) {
			return ResponseEntity.ok("Acesso ok.");
		}

		return new ResponseEntity<>("Você não está autorizado a acessar este recurso.", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("tecnico")
	public ResponseEntity<String> acessoTecnico(@RequestHeader("Authorization") String header) {

		String token = header.replace("Bearer ", "");
		String username = JwtUtil.getUsernameByToken(token);

		if (permissaoService.hasPermissaoTecnico(username)) {
			return ResponseEntity.ok("Acesso ok.");
		}

		return new ResponseEntity<>("Você não está autorizado a acessar este recurso.", HttpStatus.UNAUTHORIZED);
	}
}
