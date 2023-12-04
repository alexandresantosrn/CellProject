package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.RequisicaoPecas;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.RequisicaoPecasService;

@RestController
@CrossOrigin
@RequestMapping("requisicaopecas")
public class RequisicaoPecasController {

	private RequisicaoPecasService requisicaoPecasService;

	public RequisicaoPecasController(RequisicaoPecasService requisicaoPecasService) {
		this.requisicaoPecasService = requisicaoPecasService;
	}

	@GetMapping("requisicao-by-status")
	public ResponseEntity<List<RequisicaoPecas>> listarOrdensByStatus(@RequestParam int id) {	
		List<RequisicaoPecas> requisicoes = requisicaoPecasService.listarRequisicoesByStatus(id);
		return new ResponseEntity<>(requisicoes, HttpStatus.OK);
	}

	@PostMapping("cadastrar-requisicao")
	public ResponseEntity<String> cadastrarRequisicaoPecas(@RequestBody RequisicaoPecas requisicao) {

		try {
			requisicaoPecasService.salvarRequisicao(requisicao);
			return ResponseEntity.ok("Requisicão cadastrada com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

	@PostMapping("negar-requisicao")
	public ResponseEntity<String> negarRequisicaoPecas(@RequestBody RequisicaoPecas requisicao) {

		try {
			requisicaoPecasService.negarRequisicao(requisicao.getId());
			return ResponseEntity.ok("Requisicão rejeitada com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}
	
	@PostMapping("autorizar-requisicao")
	public ResponseEntity<String> autorizarRequisicaoPecas(@RequestBody RequisicaoPecas requisicao) {

		try {
			requisicaoPecasService.autorizarRequisicao(requisicao.getId());
			return ResponseEntity.ok("Requisicão autorizada com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

}
