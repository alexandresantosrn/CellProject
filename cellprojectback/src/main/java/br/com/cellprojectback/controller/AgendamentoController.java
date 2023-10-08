package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.repository.AgendamentoRepository;

@RestController
@CrossOrigin
public class AgendamentoController {
	
	@RequestMapping("/get-agendamentos")
	public List<Agendamento> getAgendamentos() {
		return AgendamentoRepository.getAgendamentos();
	}
}
