package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.StatusAgendamento;

public interface StatusAgendamentoRepository extends JpaRepository<StatusAgendamento, Integer> {

	StatusAgendamento findByDescricao(String descricao);
}
