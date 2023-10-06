package br.com.cellprojectback.util;

import java.util.Date;

import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.repository.TipoServicoRepository;
import br.com.cellprojectback.repository.UsuarioRepository;

public class Data {

	public static void carregarDados() {
		
		//Adicionando tipos de serviços
		TipoServico tipoServico1 = new TipoServico(1, "Conserto em Garantia");
		TipoServico tipoServico2 = new TipoServico(2, "Conserto Fora de Garantia");

		TipoServicoRepository.addTipoServico(tipoServico1);
		TipoServicoRepository.addTipoServico(tipoServico2);
		
		//Adicionando pessoa
		Pessoa pessoa1 = new Pessoa(1, "05641479403", "Alexandre Dantas dos Santos", "allexsantosrn@gmail.com", "84999494971");
		PessoaRepository.addPessoa(pessoa1);
		
		//Adicionando usuário
		Usuario usuario1 = new Usuario(1, pessoa1, new Date(), true);
		UsuarioRepository.addUsuario(usuario1);
		
		
	}
}
