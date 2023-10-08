package br.com.cellprojectback.service;

import br.com.cellprojectback.domain.Agendamento;

public class AgendamentoService {

	public boolean isAgendamentoAptoCancelamento(Agendamento agendamento) {

		if (agendamento.getStatusAgendamento().getId() == 1) {
			return true;
		}

		return false;

	}
}
