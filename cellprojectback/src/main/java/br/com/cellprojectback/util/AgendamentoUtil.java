package br.com.cellprojectback.util;

import java.time.LocalDate;

public class AgendamentoUtil {

	public static String gerarCodigoAgendamento(int maxId) {

		LocalDate dataAtual = LocalDate.now();
		int year = dataAtual.getYear();
		String codigo = "AG" + Integer.toString(year) + adicionaZerosCodigoAgendamento(Integer.toString(maxId + 1));

		return codigo;
	}

	public static String adicionaZerosCodigoAgendamento(String codigo) {

		while (codigo.length() < 5) {
			String zeros = "0";
			codigo = zeros + codigo;
		}

		return codigo;

	}
}
