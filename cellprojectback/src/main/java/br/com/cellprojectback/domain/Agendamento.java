package br.com.cellprojectback.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Agendamento {

	private int id;
	private String codigo;
	private Pessoa pessoa;
	private LocalDate dataAgendamento;
	private String horarioAgendamento;
	private TipoServico tipoServico;
	private StatusAgendamento statusAgendamento;

	public Agendamento(int id, String codigo, Pessoa pessoa, LocalDate dataAgendamento, String horarioAgendamento,
			TipoServico tipoServico, StatusAgendamento statusAgendamento) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.pessoa = pessoa;
		this.dataAgendamento = dataAgendamento;
		this.horarioAgendamento = horarioAgendamento;
		this.tipoServico = tipoServico;
		this.statusAgendamento = statusAgendamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHorarioAgendamento() {
		return horarioAgendamento;
	}

	public void setHorarioAgendamento(String horarioAgendamento) {
		this.horarioAgendamento = horarioAgendamento;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataAgendamento, horarioAgendamento, id, pessoa, statusAgendamento, tipoServico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(dataAgendamento, other.dataAgendamento)
				&& Objects.equals(horarioAgendamento, other.horarioAgendamento) && id == other.id
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(statusAgendamento, other.statusAgendamento)
				&& Objects.equals(tipoServico, other.tipoServico);
	}

}
