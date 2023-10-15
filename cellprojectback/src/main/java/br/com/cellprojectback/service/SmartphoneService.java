package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cellprojectback.domain.Smartphone;
import br.com.cellprojectback.repository.SmartphoneRepository;

public class SmartphoneService {

	@Autowired
	private SmartphoneRepository smartphoneRepository;

	/**
	 * Retorna a listagem de todos os smartphones.
	 * 
	 * @return List<Smartphone> - Todos os tipos de smartphones existentes.
	 */
	public List<Smartphone> listarSmartphones() {
		return smartphoneRepository.findAll();
	}
}
