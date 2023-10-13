package br.com.cellprojectback.util;

import java.time.LocalDate;

import br.com.cellprojectback.domain.Agendamento;
import br.com.cellprojectback.domain.Fabricante;
import br.com.cellprojectback.domain.OrdemServico;
import br.com.cellprojectback.domain.Pessoa;
import br.com.cellprojectback.domain.Smartphone;
import br.com.cellprojectback.domain.StatusAgendamento;
import br.com.cellprojectback.domain.StatusReparo;
import br.com.cellprojectback.domain.TipoServico;
import br.com.cellprojectback.domain.Usuario;
import br.com.cellprojectback.repository.AgendamentoRepository;
import br.com.cellprojectback.repository.FabricanteRepository;
import br.com.cellprojectback.repository.OrdemServicoRepository;
import br.com.cellprojectback.repository.PessoaRepository;
import br.com.cellprojectback.repository.SmartphoneRepository;
import br.com.cellprojectback.repository.StatusAgendamentoRepository;
import br.com.cellprojectback.repository.StatusReparoRepository;
import br.com.cellprojectback.repository.TipoServicoRepository;
import br.com.cellprojectback.repository.UsuarioRepository;

public class Data {

	public static void carregarDados() {		

		// Adicionando tipos de serviços
		TipoServico tipoServico1 = new TipoServico(1, "Conserto em Garantia");
		TipoServico tipoServico2 = new TipoServico(2, "Conserto Fora de Garantia");
		//TipoServicoRepository.addTipoServico(tipoServico1);
		//TipoServicoRepository.addTipoServico(tipoServico2);

		// Adicionando pessoa
		Pessoa pessoa1 = new Pessoa(1, "05641479403", "Alexandre Dantas dos Santos", "allexsantosrn@gmail.com",
				"84999494971");
		PessoaRepository.addPessoa(pessoa1);

		// Adicionando usuário
		Usuario usuario1 = new Usuario(1, "allexsantosrn@gmail.com", "123456", pessoa1, LocalDate.now(), true);
		UsuarioRepository.addUsuario(usuario1);

		// Adicionando marcas de smartphone
		Fabricante fabricante1 = new Fabricante(1, "Apple");
		FabricanteRepository.addFabricante(fabricante1);

		// Adicionando modelos de smartphone
		Smartphone smart1 = new Smartphone(1, "Iphone 12", fabricante1);
		SmartphoneRepository.addSmartphone(smart1);

		// Adicionando status de reparo
		StatusReparo statusReparo1 = new StatusReparo(1, "Pendente");
		StatusReparo statusReparo2 = new StatusReparo(2, "Em Andamento");
		StatusReparo statusReparo3 = new StatusReparo(3, "Aguardando Peças");
		StatusReparo statusReparo4 = new StatusReparo(4, "Aguardando Retirada");
		StatusReparo statusReparo5 = new StatusReparo(5, "Concluído");
		StatusReparoRepository.addStatusReparo(statusReparo1);
		StatusReparoRepository.addStatusReparo(statusReparo2);
		StatusReparoRepository.addStatusReparo(statusReparo3);
		StatusReparoRepository.addStatusReparo(statusReparo4);
		StatusReparoRepository.addStatusReparo(statusReparo5);

		// Adicionando ordens de serviço
		OrdemServico os1 = new OrdemServico(1, "OS202300001", LocalDate.now(), pessoa1, smart1, tipoServico2, statusReparo5,
				70.00);
		OrdemServicoRepository.addOrdemServico(os1);

		// Adicionando status de agendamento
		StatusAgendamento statusAgendamento1 = new StatusAgendamento(1, "Agendado");
		StatusAgendamento statusAgendamento2 = new StatusAgendamento(2, "Atendido");
		StatusAgendamento statusAgendamento3 = new StatusAgendamento(3, "Cancelado");
		//StatusAgendamentoRepository.addStatusAgendamento(statusAgendamento1);
		//StatusAgendamentoRepository.addStatusAgendamento(statusAgendamento2);
		//StatusAgendamentoRepository.addStatusAgendamento(statusAgendamento3);

		// Adicionando agendamentos
		Agendamento agendamento = new Agendamento(1, "AG202300001", pessoa1, LocalDate.now(), "15:00", tipoServico1,
				statusAgendamento1);
		AgendamentoRepository.addAgendamentos(agendamento);

	}
}
