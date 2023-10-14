package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario getUsuarioByUsuario(String usuario);

}
