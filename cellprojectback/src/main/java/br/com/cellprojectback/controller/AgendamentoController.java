package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.AgendamentoService;

@RestController
@CrossOrigin
@RequestMapping("/agendamento")
public class AgendamentoController {

	private final AgendamentoService agendamentoService;

	AgendamentoController(AgendamentoService agendamentoService) {
		this.agendamentoService = agendamentoService;
	}

	@GetMapping
	public ResponseEntity<List<Agendamento>> listarAgendamentos() {
		List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
		return new ResponseEntity<>(agendamentos, HttpStatus.OK);
	}

	@PostMapping("cadastrar-agendamento")
	public ResponseEntity<String> cadastrarAgendamento(@RequestBody Agendamento agendamento) {

		try {
			agendamentoService.salvarAgendamento(agendamento);
			return ResponseEntity.ok("Agendamento realizado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}
	
	@PostMapping("cancelar-agendamento")
	public ResponseEntity<String> cancelarAgendamento(@RequestParam int id) {

		try {			
			agendamentoService.cancelarAgendamento(id);
			return ResponseEntity.ok("Agendamento cancelado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}
	
}
