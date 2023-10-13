package br.com.cellprojectback.service;

import br.com.cellprojectback.repository.UsuarioRepository;

public class UsuarioService {

	/**
	 * Retorna true caso exista algum usuário com o e-mail informado.
	 * 
	 * @param email<String> - E-mail do usuário.
	 * @return Boolean - True ou false.
	 */
	public Boolean hasUsuarioByEmail(String email) {

		if (UsuarioRepository.hasUsuarioByEmail(email)) {
			return true;
		}

		return false;
	}
	
	/**
	 * Retorna true casos exista algum usuário com o login e senha informados.
	 * 
	 * @param email
	 * @param senha
	 * @return - True ou false.
	 */
	public Boolean hasUsuarioByLoginSenha(String email, String senha) {

		if (UsuarioRepository.hasUsuarioByEmail(email)) {
			return true;
		}

		return false;
	}


}
