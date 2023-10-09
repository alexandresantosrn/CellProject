package br.com.cellprojectback.util;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Usuario;

public class UsuarioUtil {

	public static int getNextId() {

		List<Usuario> usuarios = new ArrayList<Usuario>();

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
