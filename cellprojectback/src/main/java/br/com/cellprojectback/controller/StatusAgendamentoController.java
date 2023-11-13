package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.service.StatusAgendamentoService;

@CrossOrigin
@RestController
@RequestMapping("statusagendamento")
public class StatusAgendamentoController {

	private final StatusAgendamentoService statusAgendamentoService;

	public StatusAgendamentoController(StatusAgendamentoService statusAgendamentoService) {
		this.statusAgendamentoService = statusAgendamentoService;
	}

	@GetMapping
	public ResponseEntity<List<StatusAgendamento>> listarStatusAgendamento() {
		List<StatusAgendamento> tiposAgendamento = statusAgendamentoService.listarStatusAgendamento();
		return new ResponseEntity<>(tiposAgendamento, HttpStatus.OK);
	}

}
