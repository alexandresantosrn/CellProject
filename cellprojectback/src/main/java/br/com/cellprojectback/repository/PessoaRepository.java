package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Pessoa;

public class PessoaRepository {

	private static List<Pessoa> pessoas = new ArrayList<Pessoa>();

	public static List<Pessoa> getPessoas() {
		return pessoas;
	}

	public static Pessoa addPessoa(Pessoa pessoa) {
		pessoas.add(pessoa);
		return pessoa;
	}

}
