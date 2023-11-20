package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.OrdemServicoService;

@RestController
@CrossOrigin
@RequestMapping("ordemservico")
public class OrdemServicoController {

	private final OrdemServicoService ordemServicoService;

	OrdemServicoController(OrdemServicoService ordemServicoService) {
		this.ordemServicoService = ordemServicoService;
	}

	@GetMapping
	public ResponseEntity<List<OrdemServico>> listarOrdensServico() {
		List<OrdemServico> ordensServico = ordemServicoService.listarOrdensServico();
		return new ResponseEntity<>(ordensServico, HttpStatus.OK);
	}

	@GetMapping("ordem-by-codigo")
	public ResponseEntity<OrdemServico> listarOrdemByCodigo(@RequestParam String codigo) {
		OrdemServico ordem = ordemServicoService.findOrdembyCodigo(codigo);
		return new ResponseEntity<>(ordem, HttpStatus.OK);
	}

	@PostMapping("remover-ordem")
	public ResponseEntity<String> cancelarAgendamento(@RequestParam int id) {

		try {
			ordemServicoService.removerOrdemServico(id);
			return ResponseEntity.ok("Ordem de serviço removida com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

}
