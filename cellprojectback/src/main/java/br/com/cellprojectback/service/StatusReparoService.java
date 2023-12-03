package br.com.cellprojectback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.domain.StatusReparo;
import br.com.cellprojectback.repository.StatusReparoRepository;

@Service
public class StatusReparoService {

	@Autowired
	StatusReparoRepository statusRepository;

	/**
	 * Retorna a listagem dos status de reparo.
	 * 
	 * @return List<StatusReparo> - Todos os status de reparo existentes.
	 */
	public List<StatusReparo> listarStatusReparo() {
		return statusRepository.findAll();
	}

	/**
	 * Retorna status do reparo conforme a descrição informada.
	 * 
	 * @param descricao
	 * @return
	 */
	public StatusReparo findStatusByDescricao(String descricao) {
		return statusRepository.findByDescricao(descricao);
	}
	
	/**
	 * Retorna um status de reparo através do seu id.
	 * 
	 * @param id<Int> - Id do status do agendamento.
	 * @return StatusAgendamento - Status de agendamento recuperado.
	 */
	public Optional<StatusReparo> buscarStatusReparoporId(int id) {
		return statusRepository.findById(id);
	}
	
	
}

