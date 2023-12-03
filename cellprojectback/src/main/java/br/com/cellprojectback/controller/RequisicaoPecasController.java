package br.com.cellprojectback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping("cadastrar-requisicao")
	public ResponseEntity<String> cadastrarRequisicaoPecas(@RequestBody RequisicaoPecas requisicao) {

		try {
			requisicaoPecasService.salvarRequisicao(requisicao);
			return ResponseEntity.ok("Requisicão cadastrada com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

}
