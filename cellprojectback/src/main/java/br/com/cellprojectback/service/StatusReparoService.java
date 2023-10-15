package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cellprojectback.domain.StatusReparo;
import br.com.cellprojectback.repository.StatusReparoRepository;

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
}

