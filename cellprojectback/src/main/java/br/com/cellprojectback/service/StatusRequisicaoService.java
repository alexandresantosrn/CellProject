package br.com.cellprojectback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.StatusRequisicao;
import br.com.cellprojectback.repository.StatusRequisicaoRepository;

@Service
public class StatusRequisicaoService {

	@Autowired
	private StatusRequisicaoRepository statusRequisicaoRepository;

	/**
	 * Retorna a listagem de todos os status de requisição existentes.
	 * 
	 * @return List<StatusRequisicao> - Todos os status de requisição existentes.
	 */
	public List<StatusRequisicao> listarTodosStatusRequisicao() {
		return statusRequisicaoRepository.findAll();
	}

	/**
	 * Retorna status da requisição conforme a descrição informada.
	 * 
	 * @param descricao
	 * @return
	 */
	public StatusRequisicao findStatusByDescricao(String descricao) {
		return statusRequisicaoRepository.findByDescricao(descricao);
	}

	public Optional<StatusRequisicao> findStatusRequisicaoById(int id) {
		return statusRequisicaoRepository.findById(id);
	}

}
