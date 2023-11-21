package br.com.cellprojectback.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Smartphone;
import br.com.cellprojectback.repository.SmartphoneRepository;

@Service
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
	
	/**
	 * Retorna a listagem de modelos por fabricante informado.
	 * 
	 * @param id<Int> - id do fabricante
	 * @return List<Smartphone> - Lista dos smartphones do tipo de fabricante informado.
	 */
	public List<Smartphone> listarModelosByFabricante(int id) {

		List<Smartphone> smartphones = smartphoneRepository.findAll(Sort.by(Sort.Direction.ASC, "modelo"));
		List<Smartphone> listaSmartphones = new ArrayList<>();

		for (Smartphone smartphone : smartphones) {
			if (smartphone.getFabricante().getId() == id) {
				listaSmartphones.add(smartphone);
			}
		}

		return listaSmartphones;
	}

}
