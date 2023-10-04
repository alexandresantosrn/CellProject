package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.TipoServico;

public class TipoServicoRepository {

	private static List<TipoServico> tiposServico = new ArrayList<TipoServico>();

	public static List<TipoServico> getTiposServico() {
		return tiposServico;
	}
	
	public static TipoServico addTipoServico(TipoServico tipo) {
		tiposServico.add(tipo);
		return tipo;
	}
}
