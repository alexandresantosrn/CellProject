package br.com.cellprojectback.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.util.PessoaUtil;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	/**
	 * Retorna a listagem de todas as pessoas.
	 * 
	 * @return List<Pessoa> - Todas as pessoas existentes. '
	 */
	public List<Pessoa> listarPessoas() {
		return pessoaRepository.findAll();
	}

	/**
	 * Salva uma nova pessoa na base de dados.
	 * 
	 * @param pessoa<Pessoa> - Nova pessoa informada.
	 * @return Pessoa - Nova pessoa criada.
	 */
	public Pessoa salvarPessoa(Pessoa pessoa) {

		if (pessoa.getNome() == null || pessoa.getCpf() == null || pessoa.getEmail() == null
				|| pessoa.getTelefone() == null || pessoa.getDataNascimento() == null || pessoa.getSexo() == null) {
			throw new ServiceException("Campos obrigatórios não informados.");
		}

		else if (!PessoaUtil.isCPFValido(pessoa.getCpf())) {
			throw new ServiceException("Informe um cpf válido.");
		}

		else if (findPessoabyCPF(pessoa.getCpf()) != null) {
			throw new ServiceException("Já existe um cadastro com o cpf informado.");
		}

		else if (findPessoaByEmail(pessoa.getEmail()) != null) {
			throw new ServiceException("Já existe um cadastro com o email informado.");
		}

		pessoa.setDataCadastro(new Date());

		return pessoaRepository.save(pessoa);
	}

	/**
	 * Retorna uma pessoa através do seu cpf.
	 * 
	 * @param cpf<String> - CPF informado.
	 * @return Pessoa - Pessoa com o cpf informado.
	 */
	public Pessoa findPessoabyCPF(String cpf) {
		return pessoaRepository.findByCpf(cpf);
	}

	/**
	 * Retorna uma pessoa através do seu e-mail.
	 * 
	 * @param email<String> - E-mail informado.
	 * @return Pessoa - Pessoa com o email informado.
	 */
	public Pessoa findPessoaByEmail(String email) {
		return pessoaRepository.findByEmail(email);
	}

	/**
	 * Retorna uma pessoa através do seu id.
	 * 
	 * @param id<Int> - Id da pessoa.
	 * @return Pessoa - Pessoa com o id informado.
	 */
	public Optional<Pessoa> findPessoaById(int id) {
		return pessoaRepository.findById(id);
	}

	/**
	 * Atualiza os dados da pessoa informada.
	 * 
	 * @param pessoa<Pessoa> - Pessoa informada.
	 * @return Pessoa - Pessoa com dados atualizados.
	 */
	public Pessoa updatePessoa(Pessoa pessoa) {

		Pessoa pessoaCPF = findPessoabyCPF(pessoa.getCpf());
		Pessoa pessoaEmail = findPessoaByEmail(pessoa.getEmail());

		if (pessoa.getNome() == null || pessoa.getCpf() == null || pessoa.getEmail() == null
				|| pessoa.getTelefone() == null || pessoa.getDataNascimento() == null || pessoa.getSexo() == null) {
			throw new ServiceException("Campos obrigatórios não informados.");
		}

		else if (!PessoaUtil.isCPFValido(pessoa.getCpf())) {
			throw new ServiceException("Informe um cpf válido.");
		}

		else if (findPessoabyCPF(pessoa.getCpf()) != null && pessoaCPF.getId() != pessoa.getId()) {
			throw new ServiceException("Já existe um cadastro com o cpf informado.");
		}

		else if (findPessoaByEmail(pessoa.getEmail()) != null && pessoaEmail.getId() != pessoa.getId()) {
			throw new ServiceException("Já existe um cadastro com o email informado.");
		}

		Pessoa pessoaAtualizada = findPessoaById(pessoa.getId()).orElseThrow();

		pessoaAtualizada.setCpf(pessoa.getCpf());
		pessoaAtualizada.setNome(pessoa.getNome());
		pessoaAtualizada.setDataNascimento(pessoa.getDataNascimento());
		pessoaAtualizada.setSexo(pessoa.getSexo());
		pessoaAtualizada.setTelefone(pessoa.getTelefone());

		return pessoaRepository.save(pessoaAtualizada);
	}

}
