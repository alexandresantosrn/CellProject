package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	/**
	 * Retorna a listagem de todas as ordens de serviço.
	 * 
	 * @return List<OrdemServico> - Todos as ordens de serviço existentes.
	 */
	public List<OrdemServico> listarOrdensServico() {
		return ordemServicoRepository.findAll();
	}

}
