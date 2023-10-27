package br.com.cellprojectback.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.AgendamentoRepository;
import br.com.cellprojectback.util.AgendamentoUtil;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	private StatusAgendamentoService statusAgendamentoService;

	private PessoaService pessoaService;

	public AgendamentoService(StatusAgendamentoService statusAgendamentoService, PessoaService pessoaService) {
		this.statusAgendamentoService = statusAgendamentoService;
		this.pessoaService = pessoaService;
	}

	/**
	 * Retorna a listagem de todos os agendamentos ordenados pela data de
	 * agendamento.
	 * 
	 * @return List<Agendamento> - Todos os tipos os agendamentos existentes.
	 */
	public List<Agendamento> listarAgendamentos() {
		return agendamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "dataAgendamento"));
	}

	/**
	 * Salva um novo agendamento na base de dados.
	 * 
	 * @param agendamento<Agendamento> - Novo agendamento informado.
	 * @return Agendamento - Novo agendamento criado.
	 */
	public Agendamento salvarAgendamento(Agendamento agendamento) {

		if (agendamento.getTipoServico() == null || agendamento.getDataAgendamento() == null
				|| agendamento.getHorarioAgendamento() == null) {

			throw new ServiceException("Campos obrigatórios não informados.");
		}

		else if (agendamento.getDataAgendamento().isBefore(LocalDate.now())
				|| agendamento.getDataAgendamento().isEqual(LocalDate.now())) {

			throw new ServiceException("Não é possível registrar agendamentos para a data informada.");
		}

		else if (!isPeriodoAgendamentoDisponivel(agendamento.getDataAgendamento(),
				agendamento.getHorarioAgendamento())) {

			throw new ServiceException("Data/Horário indisponíveis para agendamento.");
		}

		agendamento.setCodigo(AgendamentoUtil.gerarCodigoAgendamento(getIdMaximoAgendamento()));
		agendamento.setPessoa(pessoaService.findPessoabyCPF("05641479403"));
		agendamento.setStatusAgendamento(statusAgendamentoService.findStatusByDescricao("Confirmado"));

		return agendamentoRepository.save(agendamento);
	}

	/**
	 * Verifica se há possibilidade de agendamento para a data e horário informados.
	 * 
	 * @param dataAgendamento<LocalDate> - Data do agendamento informada.
	 * @param horarioAgendamento<String> - Horário do agendamento informado.
	 * @return boolean - Retorna true caso o período selecionado esteja disponível
	 *         para agendamento.
	 */
	private boolean isPeriodoAgendamentoDisponivel(LocalDate dataAgendamento, String horarioAgendamento) {

		List<Agendamento> agendamentos = listarAgendamentos();

		for (Agendamento agendamento : agendamentos) {

			if ((agendamento.getDataAgendamento().isEqual(dataAgendamento))
					&& (agendamento.getHorarioAgendamento().equals(horarioAgendamento))
					&& !agendamento.getStatusAgendamento().getDescricao().equals("Cancelado")) {
				return false;

			}
		}

		return true;
	}

	/**
	 * Retorna o id máximo da listagem de agendamentos existentes.
	 * 
	 * @return int - Id máximo de agendamento.
	 */

	private int getIdMaximoAgendamento() {

		int maxId = 0;

		List<Agendamento> agendamentos = listarAgendamentos();

		for (Agendamento agendamento : agendamentos) {

			if (agendamento.getId() > maxId) {
				maxId = agendamento.getId();
			}
		}

		return maxId;
	}

	/**
	 * Realiza o cancelamento do agendamento informado.
	 * 
	 * @param id<Int> - Id do agendamento informado.
	 * @return Agendamento - Agendamento atualizado para o status: Cancelado.
	 */
	public Agendamento cancelarAgendamento(int id) {

		Agendamento agendamento = buscarAgendamentoporId(id).orElseThrow();

		if (isAgendamentoFinalizado(agendamento)) {
			throw new ServiceException(
					"O agendamento selecionado não pode mais ser cancelado, pois já se encontra finalizado.");
		}

		else if (isAgendamentoCancelado(agendamento)) {
			throw new ServiceException("O agendamento selecionado já se encontra cancelado.");
		}

		else if (agendamento.getDataAgendamento().equals(LocalDate.now())
				|| (agendamento.getDataAgendamento().isBefore(LocalDate.now()))) {

			throw new ServiceException("Não é mais possível realizar cancelamentos para este agendamento.");
		}

		return agendamentoRepository.save(agendamento);
	}

	/**
	 * Retorna um agendamento através do seu id.
	 * 
	 * @param id<Int> - Id do agendamento.
	 * @return Optional<Agendamento> - Agendamento localizado.
	 */
	public Optional<Agendamento> buscarAgendamentoporId(int id) {
		return agendamentoRepository.findById(id);
	}

	/**
	 * Retorna true caso o agendamento esteja com status cancelado.
	 * 
	 * @param agendamento<Agendamento> - Agendamento em questão.
	 * @return boolean - Retorna true caso o agendamento esteja com status
	 *         cancelado.
	 */
	public boolean isAgendamentoCancelado(Agendamento agendamento) {

		if (agendamento.getStatusAgendamento().getDescricao().equals("Cancelado")) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna true caso o agendamento esteja com status finalizado.
	 * 
	 * @param agendamento<Agendamento> - Agendamento em questão.
	 * @return boolean - Retorna true caso o agendamento esteja com status
	 *         finalizado.
	 */
	public boolean isAgendamentoFinalizado(Agendamento agendamento) {

		if (agendamento.getStatusAgendamento().getDescricao().equals("Finalizado")) {
			return true;
		}

		return false;
	}

}
