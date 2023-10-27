package br.com.cellprojectback.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

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
	 * Método auxiliar que complementa zeros à esquerda para a String de agendamento.
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

	/**
	 * Método gerador dos horários de trabalho habilitados para um agendamento
	 * diário.
	 * 
	 * @return ArrayList<String> - Array de horários.
	 */
	private static ArrayList<String> getHorariosHabilitados() {

		ArrayList<String> horarios = new ArrayList<>();

		horarios.add("08:00");
		horarios.add("08:30");
		horarios.add("09:00");
		horarios.add("09:30");
		horarios.add("10:00");
		horarios.add("10:30");
		horarios.add("11:30");
		horarios.add("12:00");
		horarios.add("12:30");
		horarios.add("13:00");
		horarios.add("14:00");
		horarios.add("15:00");
		horarios.add("15:30");
		horarios.add("16:30");
		horarios.add("17:00");
		horarios.add("17:30");

		return horarios;
	}
}
