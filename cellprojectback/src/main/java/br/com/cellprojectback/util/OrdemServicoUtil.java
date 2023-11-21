package br.com.cellprojectback.util;

import java.time.LocalDate;

public class OrdemServicoUtil {

	/**
	 * Realiza a geração do código/número das ordens de serviço realizadas.
	 * 
	 * @param maxId<Int> - Id máximo utilizado na geração do código.
	 * @return <String> - Código final.
	 */
	public static String gerarCodigoOrdem(int maxId) {

		LocalDate dataAtual = LocalDate.now();
		int year = dataAtual.getYear();
		return "OS" + Integer.toString(year) + adicionaZerosCodigoOrdem(Integer.toString(maxId + 1));
	}

	/**
	 * Método auxiliar que complementa zeros à esquerda para a String da ordem de
	 * serviço.
	 * 
	 * @param codigo<String> - Código parcial.
	 * @return codigo<String> - Código final.
	 */
	private static String adicionaZerosCodigoOrdem(String codigo) {

		while (codigo.length() < 5) {
			String zeros = "0";
			codigo = zeros + codigo;
		}

		return codigo;
	}
}
