package br.com.cellprojectback.util;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Pessoa;

public class PessoaUtil {

	public static boolean isCPFValido(String cpf) {
		// Remova caracteres não numéricos do CPF
		cpf = cpf.replaceAll("[^0-9]", "");

		// Verifique se o CPF tem 11 dígitos
		if (cpf.length() != 11) {
			return false;
		}

		// Calcula o primeiro dígito verificador
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			int digito = Character.getNumericValue(cpf.charAt(i));
			soma += digito * (10 - i);
		}
		int primeiroDigito = 11 - (soma % 11);

		if (primeiroDigito == 10 || primeiroDigito == 11) {
			primeiroDigito = 0;
		}

		// Verifica o primeiro dígito verificador
		if (primeiroDigito != Character.getNumericValue(cpf.charAt(9))) {
			return false;
		}

		// Calcula o segundo dígito verificador
		soma = 0;
		for (int i = 0; i < 10; i++) {
			int digito = Character.getNumericValue(cpf.charAt(i));
			soma += digito * (11 - i);
		}
		int segundoDigito = 11 - (soma % 11);

		if (segundoDigito == 10 || segundoDigito == 11) {
			segundoDigito = 0;
		}

		// Verifica o segundo dígito verificador
		if (segundoDigito != Character.getNumericValue(cpf.charAt(10))) {
			return false;
		}

		return true;
	}

	public static int getNextId() {

		List<Pessoa> pessoas = new ArrayList<Pessoa>();

		int var = 0;
		int temp = 0;

		for (Pessoa pessoa : pessoas) {
			temp = pessoa.getId();

			if (temp > var) {
				var = temp;
			}
		}

		return var + 1;
	}
}
