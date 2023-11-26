package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Produto findByCodigo(int codigo);

}
