package br.com.cellprojectback.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.service.UsuarioService;

@RestController
@CrossOrigin

public class LogonController {

	private final UsuarioService usuarioService;

	public LogonController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	
}
