package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.repository.StatusAgendamentoRepository;

@Service
public class StatusAgendamentoService {

	@Autowired
	private StatusAgendamentoRepository statusAgendamentoRepository;

	/**
	 * Retorna a listagem de todos os status de agendamento.
	 * 
	 * @return List<StatusAgendamento> - Todos os status de agendamento existentes.
	 */
	public List<StatusAgendamento> listarStatusAgendamento() {
		return statusAgendamentoRepository.findAll();
	}

	/**
	 * Retorna status de agendamento conforme a descrição informada.
	 * 
	 * @param descricao
	 * @return
	 */
	public StatusAgendamento findStatusByDescricao(String descricao) {
		return statusAgendamentoRepository.findByDescricao(descricao);
	}

}
