package br.com.cellprojectback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.domain.Permissao;
import br.com.cellprojectback.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository permissaoRepository;

	private UsuarioService usuarioService;

	private PapelService papelService;

	public PermissaoService(PermissaoRepository permissaoRepository, UsuarioService usuarioService,
			PapelService papelService) {
		super();
		this.permissaoRepository = permissaoRepository;
		this.usuarioService = usuarioService;
		this.papelService = papelService;
	}

	public boolean hasPermissaoAtendimento(String username) {

		Usuario usuario = usuarioService.findUsuarioByEmail(username);

		List<Permissao> permissoesUser = findPermissoesByUsuario(usuario);

		for (Permissao permissao : permissoesUser) {
			if (permissao.getPapel().equals(papelService.findPapelByDescricao("Gestor Atendimento"))) {
				return true;
			}
		}

		return false;
	}

	public boolean hasPermissaoAlmoxarifado(String username) {

		Usuario usuario = usuarioService.findUsuarioByEmail(username);

		List<Permissao> permissoesUser = findPermissoesByUsuario(usuario);

		for (Permissao permissao : permissoesUser) {
			if (permissao.getPapel().equals(papelService.findPapelByDescricao("Gestor Almoxarifado"))) {
				return true;
			}
		}

		return false;
	}

	public boolean hasPermissaoTecnico(String username) {

		Usuario usuario = usuarioService.findUsuarioByEmail(username);

		List<Permissao> permissoesUser = findPermissoesByUsuario(usuario);

		for (Permissao permissao : permissoesUser) {
			if (permissao.getPapel().equals(papelService.findPapelByDescricao("Gestor Tecnico"))) {
				return true;
			}
		}

		return false;
	}

	private List<Permissao> findPermissoesByUsuario(Usuario usuario) {
		return permissaoRepository.findByUsuario(usuario);
	}
}
