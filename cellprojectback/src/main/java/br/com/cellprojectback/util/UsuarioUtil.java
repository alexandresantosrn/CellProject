package br.com.cellprojectback.util;

import java.util.List;

import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.repository.UsuarioRepository;

public class UsuarioUtil {

	public static int getNextId() {

		List<Usuario> usuarios = UsuarioRepository.getUsuarios();

		int var = 0;
		int temp = 0;

		for (Usuario usuario : usuarios) {
			temp = usuario.getId();

			if (temp > var) {
				var = temp;
			}
		}

		return var + 1;
	}
}
