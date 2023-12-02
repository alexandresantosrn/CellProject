package br.com.cellprojectback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Papel;
import br.com.cellprojectback.repository.PapelRepository;

@Service
public class PapelService {

	@Autowired
	PapelRepository papelRepository;

	/**
	 * Retorna o papel de acordo com a descrição informada.
	 * 
	 * @param descricao
	 * @return
	 */
	public Papel findPapelByDescricao(String descricao) {
		return papelRepository.findByDescricao(descricao);
	}

}
