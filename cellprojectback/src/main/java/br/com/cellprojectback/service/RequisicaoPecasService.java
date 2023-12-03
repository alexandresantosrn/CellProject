package br.com.cellprojectback.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.RequisicaoPecas;
import br.com.cellprojectback.repository.RequisicaoPecasRepository;

@Service
public class RequisicaoPecasService {

	@Autowired
	private RequisicaoPecasRepository requisicaoPecasRepository;

	private StatusRequisicaoService statusRequisicaoService;

	public RequisicaoPecasService(RequisicaoPecasRepository requisicaoPecasRepository,
			StatusRequisicaoService statusRequisicaoService) {
		super();
		this.requisicaoPecasRepository = requisicaoPecasRepository;
		this.statusRequisicaoService = statusRequisicaoService;
	}

	public RequisicaoPecas salvarRequisicao(RequisicaoPecas requisicao) {

		requisicao.setDataSolicitacao(LocalDate.now());
		requisicao.setStatusRequisicao(statusRequisicaoService.findStatusByDescricao("Pendente"));

		return requisicaoPecasRepository.save(requisicao);
	}

}
