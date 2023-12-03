package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.StatusReparo;
import br.com.cellprojectback.service.StatusReparoService;

@RestController
@CrossOrigin
@RequestMapping("statusreparo")
public class StatusReparoController {

	private final StatusReparoService statusReparoService;

	public StatusReparoController(StatusReparoService statusReparoService) {
		super();
		this.statusReparoService = statusReparoService;
	}

	@GetMapping
	public ResponseEntity<List<StatusReparo>> listarTiposReparo() {
		List<StatusReparo> tiposReparo = statusReparoService.listarStatusReparo();
		return new ResponseEntity<>(tiposReparo, HttpStatus.OK);
	}
}
