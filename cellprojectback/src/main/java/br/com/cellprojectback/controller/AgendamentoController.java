package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.repository.AgendamentoRepository;
import br.com.cellprojectback.repository.StatusAgendamentoRepository;
import br.com.cellprojectback.service.AgendamentoService;

@RestController
@CrossOrigin
public class AgendamentoController {
	
	@RequestMapping("/get-agendamentos")
	public List<Agendamento> getAgendamentos() {
		return AgendamentoRepository.getAgendamentos();
	}
	
	@PostMapping("/cancela-agendamento")
	public ResponseEntity<String> adicionarUsuario(@RequestParam int id) {

		Agendamento agendamento = AgendamentoRepository.getAgendamentoById(id);

		if (agendamento != null) {
			StatusAgendamento statusAgendamento = StatusAgendamentoRepository.getStatusAgendamentoById(3);
			AgendamentoService agendamentoService = new AgendamentoService();
			
			if (agendamentoService.isAgendamentoAptoCancelamento(agendamento)) {
				agendamento.setStatusAgendamento(statusAgendamento);
				
				return ResponseEntity.ok("Cancelamento realizado com sucesso!");
			}						

			return new ResponseEntity<>("O agendamento selecionado não pode mais ser cancelado.", HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<>("Agendamento não localizado!", HttpStatus.NOT_FOUND);
	}
}
