package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Usuario;

public class UsuarioRepository {

	private static List<Usuario> usuarios = new ArrayList<Usuario>();

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static Usuario addUsuario(Usuario usuario) {
		usuarios.add(usuario);
		return usuario;
	}
	
	/**
	 * Verifica se há algum usuário com o e-mail informado.
	 * 
	 * @param email<String> - E-mail do usuário.
	 * @return Boolean - True ou false.
	 */
	public static boolean hasUsuarioByEmail(String email) {

		for (Usuario usuario : usuarios) {
			if (usuario.getUsuario().equals(email)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se há algum usuário com o login/email e senha informados.
	 * 
	 * @param email<String> - Email do usuário.
	 * @param senha Boolean - True ou false.
	 * @return
	 */
	public static boolean hasUsuarioByLoginSenha(String email, String senha) {
		for (Usuario usuario : usuarios) {
			if (usuario.getUsuario().equals(email)) {
				if (usuario.getSenha().equals(senha)) {
					return true;
				}
			}
		}
		return false;
	}

}
