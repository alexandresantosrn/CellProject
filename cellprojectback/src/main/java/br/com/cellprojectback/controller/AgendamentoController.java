package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.repository.AgendamentoRepository;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.repository.StatusAgendamentoRepository;
import br.com.cellprojectback.service.AgendamentoService;

@RestController
@CrossOrigin
public class AgendamentoController {

	@GetMapping("/get-agendamentos")
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

			return new ResponseEntity<>("O agendamento selecionado não pode mais ser cancelado.",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return new ResponseEntity<>("Agendamento não localizado!", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/adiciona-agendamento")
	public ResponseEntity<String> adicionarAgendamento(@RequestBody Agendamento agendamento) {

		AgendamentoService agendamentoService = new AgendamentoService();

		if (!agendamentoService.isDataAgendamentoDisponivel(agendamento)) {

			return new ResponseEntity<>("Não é possível registrar atendimentos para a data informada.",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		else if (!agendamentoService.isPeriodoAgendamentoHabilitado(agendamento)) {
			
			return new ResponseEntity<>("Data/Horário indisponíveis para agendamento.",
					HttpStatus.UNPROCESSABLE_ENTITY);
		}

		else {
			agendamento.setId(2);
			agendamento.setCodigo("AG2023002");
			Pessoa pessoa = PessoaRepository.getPessoabyCpf("05641479403");

			if (pessoa != null) {
				agendamento.setPessoa(pessoa);
			}

			StatusAgendamento statusAgendamento = StatusAgendamentoRepository.getStatusAgendamentoById(1);

			if (statusAgendamento != null) {
				agendamento.setStatusAgendamento(statusAgendamento);
			}

			AgendamentoRepository.addAgendamentos(agendamento);
			return ResponseEntity.ok("Agendamento realizado com sucesso!");
		}
	}
}
