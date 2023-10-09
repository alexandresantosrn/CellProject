package br.com.cellprojectback.util;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Agendamento;

public class AgendamentoUtil {

	public static int getNextId() {

		List<Agendamento> agendamentos = new ArrayList<Agendamento>();

		int var = 0;
		int temp = 0;

		for (Agendamento agendamento : agendamentos) {
			temp = agendamento.getId();

			if (temp > var) {
				var = temp;
			}
		}

		return var + 1;
	}
}
