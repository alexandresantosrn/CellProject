package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Usuario;

public class UsuarioRepository {
	
	private static List<Usuario> usuarios = new ArrayList<Usuario>();

	public static List<Usuario> getPessoas() {
		return usuarios;
	}
	
	public static Usuario addUsuario(Usuario usuario) {
		usuarios.add(usuario);
		return usuario;
	}

}
