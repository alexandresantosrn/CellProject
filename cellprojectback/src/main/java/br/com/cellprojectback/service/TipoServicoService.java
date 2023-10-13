package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.repository.TipoServicoRepository;

@Service
public class TipoServicoService {

	@Autowired
	private TipoServicoRepository tipoServicoRepository;

	public List<TipoServico> listarTiposServico() {
		return tipoServicoRepository.findAll();
	}
}
