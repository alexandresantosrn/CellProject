package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Agendamento;

public class AgendamentoRepository {

	private static List<Agendamento> agendamentos = new ArrayList<Agendamento>();

	public static List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public static Agendamento addAgendamentos(Agendamento agendamento) {
		agendamentos.add(agendamento);
		return agendamento;
	}

	public static Agendamento getAgendamentoById(int id) {

		for (Agendamento agendamento : agendamentos) {
			if (agendamento.getId() == id) {
				return agendamento;
			}
		}
		return null;

	}
}
