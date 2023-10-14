package br.com.cellprojectback.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private PessoaService pessoaService;

	public UsuarioService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	/**
	 * Retorna a listagem de todos os usuários.
	 * 
	 * @return List<Usuario> - Todos os usuários existentes.
	 */
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	/**
	 * Salva um novo usuário na base de dados.
	 * 
	 * @param usuario<Usuario> - Nova usuário informado.
	 * @return Usuario - Novo usuário criado.
	 */
	public Usuario salvarUsuario(Usuario usuario) {

		if (usuario.getUsuario() == null || usuario.getSenha() == null) {
			throw new ServiceException("Campos obrigatórios não informados.");
		}

		else if (findUsuarioByEmail(usuario.getUsuario()) != null) {
			throw new ServiceException("Já existe um cadastro de usuário com o email informado.");
		}

		Pessoa pessoa = pessoaService.findPessoaByEmail(usuario.getUsuario());

		if (pessoa == null) {
			throw new ServiceException("Erro ao obter dados da pessoa.");
		}

		usuario.setPessoa(pessoa);
		usuario.setAutorizado(true);
		usuario.setDataCadastro(new Date());

		return usuarioRepository.save(usuario);
	}

	/**
	 * 
	 * Retorna uma usuário através do seu e-mail.
	 * 
	 * @param usuario<String> - E-mail/usuário informado.
	 * @return - Pessoa com o email/usuário informado.
	 */
	private Usuario findUsuarioByEmail(String usuario) {
		return usuarioRepository.getUsuarioByUsuario(usuario);
	}

}
