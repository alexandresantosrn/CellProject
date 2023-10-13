package br.com.cellprojectback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.StatusAgendamento;

public interface StatusAgendamentoRepository extends JpaRepository<StatusAgendamento, Integer> {

	List<StatusAgendamento> findByDescricao(String descricao);
}
