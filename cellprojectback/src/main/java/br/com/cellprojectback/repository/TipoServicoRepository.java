package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.TipoServico;

public interface TipoServicoRepository extends JpaRepository<TipoServico, Integer> {

}
