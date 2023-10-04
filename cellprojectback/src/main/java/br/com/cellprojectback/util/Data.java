package br.com.cellprojectback.util;

import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.repository.TipoServicoRepository;

public class Data {

	public static void carregarDados() {
		TipoServico tipoServico1 = new TipoServico(1, "Conserto em Garantia");
		TipoServico tipoServico2 = new TipoServico(2, "Conserto Fora de Garantia");

		TipoServicoRepository.addTipoServico(tipoServico1);
		TipoServicoRepository.addTipoServico(tipoServico2);
	}
}
