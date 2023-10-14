package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	/**
	 * Retorna a listagem de todos os agendamentos.
	 * 
	 * @return List<Agendamento> - Todos os tipos os agendamentos existentes.
	 */
	public List<Agendamento> listarAgendamentos() {
		return agendamentoRepository.findAll();
	}

}
