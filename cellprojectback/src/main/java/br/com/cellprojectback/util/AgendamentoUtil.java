package br.com.cellprojectback.util;

import java.time.LocalDate;
import java.util.List;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.repository.AgendamentoRepository;

public class AgendamentoUtil {

	public static int getNextId() {

		List<Agendamento> agendamentos = AgendamentoRepository.getAgendamentos();

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

	public static String gerarCodigoAgendamento() {

		LocalDate dataAtual = LocalDate.now();
		int year = dataAtual.getYear();
		String stringId = Integer.toString(getNextId());

		String codigo = "AG" + Integer.toString(year) + adicionaZeros(stringId);

		return codigo;
	}

	public static String adicionaZeros(String codigo) {

		while (codigo.length() < 5) {
			String zeros = "0";
			codigo = zeros + codigo;
		}

		return codigo;

	}
}
