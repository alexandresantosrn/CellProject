package br.com.cellprojectback.service;

import java.time.LocalDate;
import java.util.List;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.repository.AgendamentoRepository;

public class AgendamentoService {

	public boolean isAgendamentoAptoCancelamento(Agendamento agendamento) {

		if (agendamento.getStatusAgendamento().getId() == 1) {
			return true;
		}

		return false;

	}

	public boolean isDataAgendamentoDisponivel(Agendamento agendamento) {

		LocalDate hoje = LocalDate.now();
		if (agendamento.getDataAgendamento().isBefore(hoje) || agendamento.getDataAgendamento().isEqual(hoje)) {
			return false;
		}

		return true;

	}

	public boolean isPeriodoAgendamentoHabilitado(Agendamento agendamento) {

		List<Agendamento> agendamentos = AgendamentoRepository.getAgendamentos();

		for (Agendamento agendamentoLocal : agendamentos) {

			if (agendamentoLocal.getDataAgendamento().isEqual(agendamento.getDataAgendamento())) {

				if (agendamentoLocal.getHorarioAgendamento().equals(agendamento.getHorarioAgendamento())) {
					return false;
				}
			}
		}

		return true;

	}
}
