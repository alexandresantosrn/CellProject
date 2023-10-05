package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.repository.TipoServicoRepository;

@CrossOrigin
@RestController
public class TipoServicoController {
	
	@RequestMapping("/tiposervico")
	public List<TipoServico> getTiposServico() {
		return TipoServicoRepository.getTiposServico();
	}

}
