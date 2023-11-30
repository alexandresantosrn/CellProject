package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.StatusRequisicao;
import br.com.cellprojectback.service.StatusRequisicaoService;

@CrossOrigin
@RestController
@RequestMapping("statusrequisicao")
public class StatusRequisicaoController {

	private final StatusRequisicaoService statusRequisicaoService;

	public StatusRequisicaoController(StatusRequisicaoService statusRequisicaoService) {
		this.statusRequisicaoService = statusRequisicaoService;
	}

	@GetMapping
	public ResponseEntity<List<StatusRequisicao>> listarStatusRequisicao() {
		List<StatusRequisicao> statusRequisicao = statusRequisicaoService.listarTodosStatusRequisicao();
		return new ResponseEntity<>(statusRequisicao, HttpStatus.OK);
	}

}
