package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Smartphone;
import br.com.cellprojectback.service.SmartphoneService;

@CrossOrigin
@RestController
@RequestMapping("/smartphone")
public class SmartphoneController {
	
	private final SmartphoneService smartphoneService;
	
	public SmartphoneController(SmartphoneService smartphoneService) {
		this.smartphoneService = smartphoneService;
	}
	
	@GetMapping
	public ResponseEntity<List<Smartphone>> listarSmartphones() {
		List<Smartphone> smartphones = smartphoneService.listarSmartphones(); 
		return new ResponseEntity<>(smartphones, HttpStatus.OK);
	}
	
	@GetMapping("modelo-by-fabricante")
	public ResponseEntity<List<Smartphone>> listarSmartphonesByFabricante(@RequestParam int id) {
		List<Smartphone> smartphones = smartphoneService.listarModelosByFabricante(id); 
		return new ResponseEntity<>(smartphones, HttpStatus.OK);
	}
}
