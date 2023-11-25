package br.com.cellprojectback.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.domain.Pessoa;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

	List<Agendamento> findByDataAgendamento(LocalDate dataAgendamento);

	List<Agendamento> findByPessoa(Pessoa pessoa);

}
