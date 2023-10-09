package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.StatusAgendamento;

public class StatusAgendamentoRepository {

	private static List<StatusAgendamento> tiposAgendamento = new ArrayList<StatusAgendamento>();

	public static List<StatusAgendamento> getStatusAgendamento() {
		return tiposAgendamento;
	}

	public static StatusAgendamento addStatusAgendamento(StatusAgendamento statusAgendamento) {
		tiposAgendamento.add(statusAgendamento);
		return statusAgendamento;
	}

	public static StatusAgendamento getStatusAgendamentoById(int id) {

		for (StatusAgendamento statusAgendamento : tiposAgendamento) {
			if (statusAgendamento.getId() == id) {
				return statusAgendamento;
			}
		}
		return null;

	}

}
