package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	Pessoa findByCpf(String cpf);

	Pessoa findByEmail(String email);

}
