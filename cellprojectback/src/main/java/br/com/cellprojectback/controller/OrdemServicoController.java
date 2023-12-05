package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.service.OrdemServicoService;
import security.JwtUtil;

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
	
	@GetMapping("ordem-by-status")
	public ResponseEntity<List<OrdemServico>> listarOrdensByStatus(@RequestParam int id) {
		List<OrdemServico> ordensServico = ordemServicoService.listarOrdensServicoByStatus(id);
		return new ResponseEntity<>(ordensServico, HttpStatus.OK);
	}

	@GetMapping("ordem-by-user")
	public ResponseEntity<List<OrdemServico>> listarOrdensServicoByUser(@RequestHeader("Authorization") String header) {
		String token = header.replace("Bearer ", "");
		String username = JwtUtil.getUsernameByToken(token);
		List<OrdemServico> ordensServico = ordemServicoService.listarOrdensServicoByUser(username);
		return new ResponseEntity<>(ordensServico, HttpStatus.OK);
	}

	@GetMapping("ordem-by-codigo")
	public ResponseEntity<OrdemServico> listarOrdemByCodigo(@RequestParam String codigo) {
		OrdemServico ordem = ordemServicoService.findOrdembyCodigo(codigo);
		return new ResponseEntity<>(ordem, HttpStatus.OK);
	}

	@PostMapping("remover-ordem")
	public ResponseEntity<String> cancelarAgendamento(@RequestBody OrdemServico ordem) {

		try {
			ordemServicoService.removerOrdemServico(ordem.getId());
			return ResponseEntity.ok("Ordem de serviço removida com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}

	@PostMapping("cadastrar-ordem")
	public ResponseEntity<String> cadastrarOrdemServico(@RequestBody OrdemServico ordem) {

		try {
			ordemServicoService.salvarOrdemServico(ordem);
			return ResponseEntity.ok("Cadastro realizado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}
	
	@PostMapping("iniciar-reparo")
	public ResponseEntity<String> iniciarReparo(@RequestBody OrdemServico ordem) {

		try {
			ordemServicoService.iniciarReparo(ordem.getId());
			return ResponseEntity.ok("Atendimento iniciado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}	
	
	@PostMapping("finalizar-reparo")
	public ResponseEntity<String> finalizarReparo(@RequestBody OrdemServico ordem) {

		try {
			ordemServicoService.finalizarReparo(ordem.getId());
			return ResponseEntity.ok("Conserto finalizado com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}	
	
	@PostMapping("atualizar-ordem")
	public ResponseEntity<String> atualizarOrdem(@RequestBody OrdemServico ordem) {

		try {
			ordemServicoService.atualizarOrdem(ordem.getId());
			return ResponseEntity.ok("Ordem atualizada com sucesso.");

		} catch (ServiceException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}

	}	
	
	
}
