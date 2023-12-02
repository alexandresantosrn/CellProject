package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Papel;

public interface PapelRepository extends JpaRepository<Papel, Integer> {

	Papel findByDescricao(String descricao);

}
