package br.com.cellprojectback.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.OrdemServicoRepository;
import br.com.cellprojectback.util.OrdemServicoUtil;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	private StatusReparoService statusReparoService;
	
	private PessoaService pessoaService;

	public OrdemServicoService(StatusReparoService statusReparoService, PessoaService pessoaService) {
		this.statusReparoService = statusReparoService;
		this.pessoaService = pessoaService;
	}

	/**
	 * Retorna a listagem de todas as ordens de serviço.
	 * 
	 * @return List<OrdemServico> - Todos as ordens de serviço existentes.
	 */
	public List<OrdemServico> listarOrdensServico() {
		return ordemServicoRepository.findAll();
	}

	/**
	 * Retorna a ordem de serviço através do seu id.
	 * 
	 * @param codigo<String> - Código da ordem de serviço.
	 * @return OrdemServico - Ordem de serviço retornada.
	 */
	public OrdemServico findOrdembyCodigo(String codigo) {
		return ordemServicoRepository.findByCodigo(codigo);
	}

	/**
	 * Remove a ordem de serviço através do seu id.
	 * 
	 * @param id<Int> - Id da ordem de serviço.
	 */
	public void removerOrdemServico(int id) {
		ordemServicoRepository.deleteById(id);
	}

	/**
	 * Salva uma nova ordem de serviço na base de dados.
	 * 
	 * @param ordem<OrdemServico> - Nova ordem de serviço.
	 * @return OrdemServico - Nova ordem criada.
	 */
	public OrdemServico salvarOrdemServico(OrdemServico ordem) {

		if (ordem.getPessoa() == null) {
			throw new ServiceException("Informe um cpf válido.");
		}

		else if (ordem.getPessoa() == null || ordem.getImei() == null || ordem.getSmartphone() == null
				|| ordem.getTipoServico() == null) {

			throw new ServiceException("Campos obrigatórios não informados.");
		}

		ordem.setDataEntrada(LocalDate.now());
		ordem.setCodigo(OrdemServicoUtil.gerarCodigoOrdem(getIdMaximoOrdemServico()));
		ordem.setPrecoTotal(0.0);
		ordem.setStatusReparo(statusReparoService.findStatusByDescricao("Pendente"));
		return ordemServicoRepository.save(ordem);
	}

	/**
	 * Retorna o id máximo da listagem de ordens de serviço existentes.
	 * 
	 * @return int - Id máximo da ordem de serviço.
	 */
	private int getIdMaximoOrdemServico() {

		int maxId = 0;

		List<OrdemServico> ordens = listarOrdensServico();

		for (OrdemServico ordem : ordens) {

			if (ordem.getId() > maxId) {
				maxId = ordem.getId();
			}
		}

		return maxId;
	}

	public List<OrdemServico> listarOrdensServicoByUser(String username) {		
		Pessoa pessoa = pessoaService.findPessoaByEmail(username);			
		return ordemServicoRepository.findByPessoa(pessoa);
	}

}
