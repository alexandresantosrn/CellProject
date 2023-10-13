package br.com.cellprojectback.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.repository.UsuarioRepository;
import br.com.cellprojectback.service.UsuarioService;
import br.com.cellprojectback.util.UsuarioUtil;

@CrossOrigin
@RestController
public class UsuarioController {

	UsuarioService usuarioService = new UsuarioService();

	@GetMapping("/get-usuarios")
	public List<Usuario> getUsuarios() {
		return UsuarioRepository.getUsuarios();
	}

	@PostMapping("/cadastra-usuario")
	public ResponseEntity<String> adicionarUsuario(@RequestParam String email, String senha, String cpf) {

		Pessoa pessoa = PessoaRepository.getPessoabyCpf(cpf);

		if (pessoa != null) {
			Usuario user = new Usuario(UsuarioUtil.getNextId(), email, senha, pessoa, LocalDate.now(), true);
			UsuarioRepository.addUsuario(user);

			return ResponseEntity.ok("Cadastro realizado com sucesso!");
		}

		return new ResponseEntity<>("Erro na criação do usuário!", HttpStatus.NOT_FOUND);
	}

	/**
	 * Envia um e-mail de recuperação de senha para o usuário.
	 * 
	 * @param email<String> - E-mail informado pelo usuário.
	 * @return ResponseEntity<String> - Retorna mensagem de sucesso ou falha.
	 */
	@PostMapping("/enviar-email")
	public ResponseEntity<String> enviarEmailRecuperacaoSenha(@RequestParam String email) {

		if (usuarioService.hasUsuarioByEmail(email)) {
			return ResponseEntity.ok("E-mail de recuperação de senha enviado com sucesso.");
		}

		return new ResponseEntity<>("E-mail não localizado na base de dados.", HttpStatus.NOT_FOUND);
	}

	/**
	 * Realiza o registro do login do usuário no sistema.
	 * 
	 * @param email<String> - E-mail informado pelo usuário.
	 * @param senha<String> - Senha informada pelo usuário.
	 * @return
	 */
	@PostMapping("/realizar-login")
	public ResponseEntity<String> realizarLogin(@RequestParam String email, String senha) {

		if (usuarioService.hasUsuarioByLoginSenha(email, senha)) {
			return ResponseEntity.ok("Usuário autenticado com sucesso!");
		}

		return new ResponseEntity<>("Email ou senha incorretos. Tente outra vez!", HttpStatus.NOT_FOUND);
	}

}
