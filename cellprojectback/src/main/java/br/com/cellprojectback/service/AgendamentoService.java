package br.com.cellprojectback.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.domain.StatusAgendamento;
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
	 * Retorna a listagem dos agendamentos ordenados pela data de agendamento.
	 * 
	 * @return List<Agendamento> - Os agendamentos existentes (Exceto os
	 *         cancelados).
	 */
	public List<Agendamento> listarAgendamentosAtivos() {
		List<Agendamento> agendamentos = agendamentoRepository.findAll(Sort.by(Sort.Direction.ASC, "dataAgendamento"));
		List<Agendamento> listaAgendamentos = new ArrayList<>();

		for (Agendamento agendamento : agendamentos) {

			if (!isAgendamentoCancelado(agendamento)) {
				listaAgendamentos.add(agendamento);
			}
		}

		return listaAgendamentos;
	}
	
	/**
	 * Retorna a listagem de agendamentos através da data de agendamento e status.
	 * 
	 * @param dataAgendamento - Data informada.
	 * @param idStatusAgendamento - Id do status informado.
	 * @return List<Agendamento> - Agendamentos desejados.
	 */
	public List<Agendamento> listarAgendamentosAtivosByDataStatus(String dataAgendamento, int idStatusAgendamento) {

		// Convertendo a data informada em LocalDate.
		LocalDate data = LocalDate.parse(dataAgendamento);

		// Retornando o status do agendamento através do seu id.
		StatusAgendamento statusAgendamento = statusAgendamentoService.buscarStatusAgendamentoporId(idStatusAgendamento)
				.orElseThrow();

		List<Agendamento> agendamentos = findAgendamentosbyDate(data);
		List<Agendamento> listaAgendamentos = new ArrayList<>();

		for (Agendamento agendamento : agendamentos) {

			if (agendamento.getStatusAgendamento().getDescricao().equals(statusAgendamento.getDescricao())) {
				listaAgendamentos.add(agendamento);
			}
		}

		return listaAgendamentos;
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

		// Não permite marcar agendamentos para datas passadas.
		else if (agendamento.getDataAgendamento().isBefore(LocalDate.now())) {
			throw new ServiceException("Não é possível cadastrar agendamentos para datas passadas.");
		}

		// Não permite marcar agendamentos para a data atual.
		else if (agendamento.getDataAgendamento().isEqual(LocalDate.now())) {
			throw new ServiceException("Não é possível cadastrar agendamentos para a data atual.");
		}

		// Verifica se a data/horário marcado encontram-se disponíveis.
		else if (!isPeriodoAgendamentoDisponivel(agendamento.getDataAgendamento(),
				agendamento.getHorarioAgendamento())) {

			throw new ServiceException("Data/Horário indisponíveis para agendamento.");
		}

		agendamento.setCodigo(AgendamentoUtil.gerarCodigoAgendamento(getIdMaximoAgendamento()));
		agendamento.setPessoa(pessoaService.findPessoabyCPF("05641479403"));
		agendamento.setStatusAgendamento(statusAgendamentoService.findStatusByDescricao("Confirmado"));
		agendamento.setDataCadastro(new Date());

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

		// Caso haja agendamentos para a data e horário informados, retorna false,
		// exceto se o agendamento já estiver cancelado.
		for (Agendamento agendamento : agendamentos) {

			if ((agendamento.getDataAgendamento().isEqual(dataAgendamento))
					&& (agendamento.getHorarioAgendamento().equals(horarioAgendamento))
					&& !isAgendamentoCancelado(agendamento)) {

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

		Agendamento agendamento = findAgendamentoById(id).orElseThrow();

		// Não permite cancelar agendamentos já atendidos.
		if (isAgendamentoAtendido(agendamento)) {
			throw new ServiceException(
					"O agendamento selecionado não pode mais ser cancelado, pois este já se encontra atendido.");
		}

		// Não permite cancelar agendamentos já cancelados.
		else if (isAgendamentoCancelado(agendamento)) {
			throw new ServiceException("O agendamento selecionado já encontra-se cancelado.");
		}

		// Não permite cancelar agendamentos agendados para a data atual.
		else if (agendamento.getDataAgendamento().equals(LocalDate.now())) {
			throw new ServiceException("Não é possível cancelar agendamentos marcados para a data de hoje.");
		}

		// Não permite cancelar agendamentos agendados para datas passadas.
		else if (agendamento.getDataAgendamento().isBefore(LocalDate.now())) {
			throw new ServiceException("Não é possível cancelar agendamentos marcados para datas passadas.");
		}

		// Seta o agendamento para o status cancelado.
		agendamento.setStatusAgendamento(statusAgendamentoService.findStatusByDescricao("Cancelado"));

		return agendamentoRepository.save(agendamento);
	}

	/**
	 * Retorna um agendamento através do seu id.
	 * 
	 * @param id<Int> - Id do agendamento.
	 * @return Optional<Agendamento> - Agendamento localizado.
	 */
	private Optional<Agendamento> findAgendamentoById(int id) {
		return agendamentoRepository.findById(id);
	}

	/**
	 * Retorna true caso o agendamento esteja com status cancelado.
	 * 
	 * @param agendamento<Agendamento> - Agendamento em questão.
	 * @return boolean - Retorna true caso o agendamento esteja com status
	 *         cancelado.
	 */
	private boolean isAgendamentoCancelado(Agendamento agendamento) {

		if (agendamento.getStatusAgendamento().getDescricao().equals("Cancelado")) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna true caso o agendamento esteja com status atendido.
	 * 
	 * @param agendamento<Agendamento> - Agendamento em questão.
	 * @return boolean - Retorna true caso o agendamento esteja com status
	 *         finalizado.
	 */
	private boolean isAgendamentoAtendido(Agendamento agendamento) {

		if (agendamento.getStatusAgendamento().getDescricao().equals("Atendido")) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna a listagem de horários disponíveis para agendamento.
	 * 
	 * @param dataAgendamento<String> - Data informada pelo usuário.
	 * @return List<String> - Horários disponíveis para agendamento.
	 */
	public List<String> carregarHorarios(String dataAgendamento) {

		// Convertendo a data informada em LocalDate.
		LocalDate data = LocalDate.parse(dataAgendamento);

		// Lista de agendamentos realizados para a data informada.
		List<Agendamento> agendamentos = findAgendamentosbyDate(data);

		// Lista padrão de horários disponíveis.
		List<String> horarios = AgendamentoUtil.getHorariosDisponiveis();

		// Lista que armazena os horários já agendados.
		List<String> horariosAgendados = new ArrayList<>();

		for (Agendamento agendamento : agendamentos) {
			if (!isAgendamentoCancelado(agendamento)) {
				horariosAgendados.add(agendamento.getHorarioAgendamento());
			}
		}

		// Remove da listagem os horários já agendados.
		horarios.removeAll(horariosAgendados);

		return horarios;
	}

	/**
	 * Retorna a listagem de agendamentos registrados para a data informada.
	 * 
	 * @param dataAgendamento<LocalDate> - Data informada;
	 * @return List<Agendamento> - Agendamentos registrados para a data informada.
	 */
	private List<Agendamento> findAgendamentosbyDate(LocalDate dataAgendamento) {
		return agendamentoRepository.findByDataAgendamento(dataAgendamento);
	}

	/**
	 * Inicia um agendamento, setando seu status para atendido.
	 * 
	 * @param id<Int> - Id do agendamento.
	 * @return Agendamento - Agendamento é atualizado.
	 */
	public Agendamento iniciarAgendamento(int id) {

		Agendamento agendamento = findAgendamentoById(id).orElseThrow();

		// Não permite iniciar agendamentos já atendidos.
		if (isAgendamentoAtendido(agendamento)) {
			throw new ServiceException(
					"O agendamento selecionado não pode ser iniciado, pois este já encontra-se atendido.");
		}

		// Não permite iniciar agendamentos já cancelados.
		else if (isAgendamentoCancelado(agendamento)) {
			throw new ServiceException(
					"O agendamento selecionado não pode ser iniciado, pois este já encontra-se cancelado.");
		}

		// Não permite atender agendamentos realizados para datas diferentes da data
		// atual.
		else if (!agendamento.getDataAgendamento().equals(LocalDate.now())) {
			throw new ServiceException(
					"Não é possível iniciar atendimentos marcados para datas futuras e/ou passadas.");
		}

		// Seta o agendamento para o status cancelado.
		agendamento.setStatusAgendamento(statusAgendamentoService.findStatusByDescricao("Atendido"));

		return agendamentoRepository.save(agendamento);
	}
}
