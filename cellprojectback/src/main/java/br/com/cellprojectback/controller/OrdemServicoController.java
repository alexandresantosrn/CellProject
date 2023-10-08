package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.repository.OrdemServicoRepository;

@RestController
@CrossOrigin
public class OrdemServicoController {

	@RequestMapping("/get-ordens-servico")
	public List<OrdemServico> getOrdensServico() {
		return OrdemServicoRepository.getOrdensServico();
	}
}
