package br.com.cellprojectback.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.repository.TipoServicoRepository;
import br.com.cellprojectback.service.TipoServicoService;

@RestController
@CrossOrigin
@RequestMapping("/tiposervico")
public class TipoServicoController {

	private final TipoServicoService tipoServicoService;

    TipoServicoController(TipoServicoService tipoServicoService) {
        this.tipoServicoService = tipoServicoService;
    }
    
	@GetMapping
	public ResponseEntity<List<TipoServico>> listarTiposServico() {
		List<TipoServico> tiposServico = tipoServicoService.listarTiposServico();
        return new ResponseEntity<>(tiposServico, HttpStatus.OK);
	}
}
