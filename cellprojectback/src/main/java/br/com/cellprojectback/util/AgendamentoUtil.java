package br.com.cellprojectback.util;

import java.time.LocalDate;

public class AgendamentoUtil {

	/**
	 * Realiza a geração do código/número dos agendamentos realizados.
	 * 
	 * @param maxId<Int> - Id máximo utilizado na geração do código.
	 * @return <String> - Código final.
	 */
	public static String gerarCodigoAgendamento(int maxId) {

		LocalDate dataAtual = LocalDate.now();
		int year = dataAtual.getYear();
		return "AG" + Integer.toString(year) + adicionaZerosCodigoAgendamento(Integer.toString(maxId + 1));
	}

	/**
	 * Método auxiliar que complementa zeros à esquerda para a String informada.
	 * 
	 * @param codigo<String> - Código parcial.
	 * @return codigo<String> - Código final.
	 */
	private static String adicionaZerosCodigoAgendamento(String codigo) {

		while (codigo.length() < 5) {
			String zeros = "0";
			codigo = zeros + codigo;
		}

		return codigo;
	}
}
