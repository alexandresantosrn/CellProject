package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.repository.TipoServicoRepository;

@CrossOrigin
@RestController
public class TipoServicoController {

	@GetMapping("/get-tipos-servico")
	public List<TipoServico> getTiposServico() {
		return TipoServicoRepository.getTiposServico();
	}
}
