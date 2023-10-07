package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Pessoa;
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

	public static boolean hasUsuarioByEmail(String email) {

		for (Usuario usuario : usuarios) {
			if (usuario.getUsuario().equals(email)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasUsuarioByLoginSenha(String email, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario.getUsuario().equals(email)) {
				if(usuario.getSenha().equals(senha)) {
					return true;
				}
			}
		}
		return false;
	}

}
