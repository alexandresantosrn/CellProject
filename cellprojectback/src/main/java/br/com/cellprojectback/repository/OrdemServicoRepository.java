package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.OrdemServico;

public class OrdemServicoRepository {

	private static List<OrdemServico> ordensServico = new ArrayList<OrdemServico>();

	public static List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public static OrdemServico addOrdemServico(OrdemServico ordemServico) {
		ordensServico.add(ordemServico);
		return ordemServico;
	}
}
