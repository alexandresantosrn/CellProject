package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Fabricante;
import br.com.cellprojectback.service.FabricanteService;

@CrossOrigin
@RestController
@RequestMapping("/fabricante")
public class FabricanteController {

	private final FabricanteService fabricanteService;

	public FabricanteController(FabricanteService fabricanteService) {
		this.fabricanteService = fabricanteService;
	}

	@GetMapping
	public ResponseEntity<List<Fabricante>> listarFabricantes() {
		List<Fabricante> fabricantes = fabricanteService.listarFabricantes();
		return new ResponseEntity<>(fabricantes, HttpStatus.OK);
	}
}
