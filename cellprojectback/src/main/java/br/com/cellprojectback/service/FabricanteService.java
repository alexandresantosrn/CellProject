package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Fabricante;
import br.com.cellprojectback.repository.FabricanteRepository;

@Service
public class FabricanteService {

	@Autowired
	private FabricanteRepository fabricanteRepository;

	/**
	 * Retorna a listagem de todos os fabricantes
	 * 
	 * @return List<Fabricante> - Todos os fabricantes existentes.
	 */
	public List<Fabricante> listarFabricantes() {
		return fabricanteRepository.findAll();
	}
}
