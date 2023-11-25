package br.com.cellprojectback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.domain.Pessoa;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {

	OrdemServico findByCodigo(String codigo);

	List<OrdemServico> findByPessoa(Pessoa pessoa);

}
