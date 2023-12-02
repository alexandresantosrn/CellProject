package br.com.cellprojectback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Permissao;
import br.com.cellprojectback.domain.Usuario;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {

	List<Permissao> findByUsuario(Usuario usuario);

}
