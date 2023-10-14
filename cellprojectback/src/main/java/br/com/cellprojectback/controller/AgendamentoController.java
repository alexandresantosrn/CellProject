package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.repository.AgendamentoRepository;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.repository.StatusAgendamentoRepository;
import br.com.cellprojectback.service.AgendamentoService;
import br.com.cellprojectback.service.PessoaService;
import br.com.cellprojectback.service.StatusAgendamentoService;
import br.com.cellprojectback.service.TipoServicoService;
import br.com.cellprojectback.util.AgendamentoUtil;

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

}
