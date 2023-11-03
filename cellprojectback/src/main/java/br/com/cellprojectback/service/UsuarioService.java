package br.com.cellprojectback.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.exception.ServiceException;
import br.com.cellprojectback.repository.UsuarioRepository;
import security.BcryptUtil;

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

		if (usuario.getLogin() == null || usuario.getSenha() == null) {
			throw new ServiceException("Campos obrigatórios não informados.");
		}

		else if (findUsuarioByEmail(usuario.getLogin()) != null) {
			throw new ServiceException("Já existe um cadastro de usuário com o email informado.");
		}

		Pessoa pessoa = pessoaService.findPessoaByEmail(usuario.getLogin());

		if (pessoa == null) {
			throw new ServiceException("Erro ao obter dados da pessoa.");
		}

		usuario.setPessoa(pessoa);
		usuario.setAutorizado(true);
		usuario.setDataCadastro(new Date());
		usuario.setSenha(BcryptUtil.cryptPassword(usuario.getSenha()));

		return usuarioRepository.save(usuario);
	}

	/**
	 * 
	 * Retorna um usuário através do seu e-mail.
	 * 
	 * @param login<String> - E-mail/login informado.
	 * @return Usuario - Usuário com o email/usuário informado.
	 */
	private Usuario findUsuarioByEmail(String login) {
		return usuarioRepository.findByLogin(login);
	}

	/**
	 * 
	 * Realiza a recuperação de senha do usuário.
	 * 
	 * @param email<String> - Email/login informado.
	 * @return Usuario - Usuário com o email/login informado.
	 */
	public Usuario recuperarSenha(String email) {

		if (email == null) {
			throw new ServiceException("Campo obrigatório não informado.");
		}

		if (findUsuarioByEmail(email) == null) {
			throw new ServiceException("Email não localizado na base de dados.");
		}

		return findUsuarioByEmail(email);
	}

	/**
	 * Realizar o registro do login do usuário no sistema.
	 * 
	 * @param login<String> - E-mail/login informado.
	 * @param senha<String  - Senha do usuário.
	 * @return Usuario - Usuário autenticado no sistema.
	 */
	public Usuario realizarLogin(String login, String senha) {

		if (login == null || senha == null) {
			throw new ServiceException("Campos obrigatórios não informado.");
		}

		Usuario usuario = findUsuarioByEmail(login);

		boolean senhaCorreta = BcryptUtil.isMatchPassword(senha, usuario.getSenha());

		if (!senhaCorreta) {
			throw new ServiceException("Email ou senha incorretos. Tente outra vez!");
		}

//		else if (findUsuarioByLoginSenha(login, senha) == null) {
//			throw new ServiceException("Email ou senha incorretos. Tente outra vez!");
//		}

		return findUsuarioByLoginSenha(login, senha);
	}

	/**
	 * Localiza um usuário através do seu login e senha.
	 * 
	 * @param login<String> - E-mail/login informado.
	 * @param senha<String  - Senha do usuário.
	 * @return
	 */
	private Usuario findUsuarioByLoginSenha(String login, String senha) {
		return usuarioRepository.findByLoginAndSenha(login, senha);
	}

}
