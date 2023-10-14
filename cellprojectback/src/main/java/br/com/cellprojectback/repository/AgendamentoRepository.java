package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{

}
