package br.com.cellprojectback.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Produto;
import br.com.cellprojectback.domain.RequisicaoPecas;
import br.com.cellprojectback.domain.StatusRequisicao;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.RequisicaoPecasRepository;

@Service
public class RequisicaoPecasService {

	@Autowired
	private RequisicaoPecasRepository requisicaoPecasRepository;

	private StatusRequisicaoService statusRequisicaoService;

	private ProdutoService produtoService;

	public RequisicaoPecasService(RequisicaoPecasRepository requisicaoPecasRepository,
			StatusRequisicaoService statusRequisicaoService, ProdutoService produtoService) {
		super();
		this.requisicaoPecasRepository = requisicaoPecasRepository;
		this.statusRequisicaoService = statusRequisicaoService;
		this.produtoService = produtoService;
	}

	public List<RequisicaoPecas> listarRequisicoes() {
		return requisicaoPecasRepository.findAll();
	}

	public RequisicaoPecas salvarRequisicao(RequisicaoPecas requisicao) {

		requisicao.setDataSolicitacao(LocalDate.now());
		requisicao.setStatusRequisicao(statusRequisicaoService.findStatusByDescricao("Pendente"));

		return requisicaoPecasRepository.save(requisicao);
	}

	public List<RequisicaoPecas> listarRequisicoesByStatus(int id) {
		System.out.println(id);
		// Retornando o status da requisição através do seu id.
		StatusRequisicao statusRequisicao = statusRequisicaoService.findStatusRequisicaoById(id).orElseThrow();

		List<RequisicaoPecas> requisicoes = listarRequisicoes();
		List<RequisicaoPecas> listaRequisicoes = new ArrayList<>();

		for (RequisicaoPecas requisicao : requisicoes) {

			if (requisicao.getStatusRequisicao().getDescricao().equals(statusRequisicao.getDescricao())) {
				listaRequisicoes.add(requisicao);
			}
		}

		return listaRequisicoes;
	}

	public RequisicaoPecas negarRequisicao(int id) {

		RequisicaoPecas requisicao = findOrdemById(id).orElseThrow();

		if (!requisicao.getStatusRequisicao().getDescricao().equals("Pendente")) {
			throw new ServiceException("Requisição não se encontra com status pendente.");
		}

		requisicao.setStatusRequisicao(statusRequisicaoService.findStatusByDescricao("Negada"));

		return requisicaoPecasRepository.save(requisicao);
	}

	private Optional<RequisicaoPecas> findOrdemById(int id) {
		return requisicaoPecasRepository.findById(id);
	}

	public RequisicaoPecas autorizarRequisicao(int id) {

		RequisicaoPecas requisicao = findOrdemById(id).orElseThrow();

		Produto produto = requisicao.getProduto();

		if (!requisicao.getStatusRequisicao().getDescricao().equals("Pendente")) {
			throw new ServiceException("Requisição não se encontra com status pendente.");
		}

		else if (produto.getQuantidade() < requisicao.getQuantidade()) {
			throw new ServiceException("Não há saldo em estoque suficiente para autorização da requisição.");
		}

		requisicao.setStatusRequisicao(statusRequisicaoService.findStatusByDescricao("Autorizada"));

		return requisicaoPecasRepository.save(requisicao);
	}

}
