package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.AgendamentoService;
import security.JwtUtil;

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
		List<Agendamento> agendamentos = agendamentoService.listarAgendamentosAtivos();
		return new ResponseEntity<>(agendamentos, HttpStatus.OK);
	}

	@GetMapping("agendamento-by-user")
	public ResponseEntity<List<Agendamento>> listarAgendamentosByUser(@RequestHeader("Authorization") String header) {
		String token = header.replace("Bearer ", "");
		String username = JwtUtil.getUsernameByToken(token);
		List<Agendamento> agendamentos = agendamentoService.listarAgendamentosByUser(username);
		return new ResponseEntity<>(agendamentos, HttpStatus.OK);
	}

	@GetMapping("list-by-data")
	public ResponseEntity<List<Agendamento>> listarAgendamentosByDataStatus(@RequestParam String dataAgendamento,
			int id) {

		List<Agendamento> agendamentos = agendamentoService.listarAgendamentosAtivosByDataStatus(dataAgendamento, id);
		return new ResponseEntity<>(agendamentos, HttpStatus.OK);
	}

	@PostMapping("cadastrar-agendamento")
	public ResponseEntity<String> cadastrarAgendamento(@RequestBody Agendamento agendamento,
			@RequestHeader("Authorization") String header) {
		
		String token = header.replace("Bearer ", "");
		String username = JwtUtil.getUsernameByToken(token);
		
		try {
			agendamentoService.salvarAgendamento(agendamento, username);
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

	@GetMapping("carregar-horarios")
	public ResponseEntity<List<String>> carregarHorarios(@RequestParam String dataAgendamento) {
		List<String> horarios = agendamentoService.carregarHorarios(dataAgendamento);
		return new ResponseEntity<>(horarios, HttpStatus.OK);
	}

	@PostMapping("iniciar-agendamento")
	public ResponseEntity<String> iniciarAgendamento(@RequestParam int id) {

		try {
			agendamentoService.iniciarAgendamento(id);
			return ResponseEntity.ok("Agendamento iniciado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

}
