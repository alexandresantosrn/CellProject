package br.com.cellprojectback.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Agendamento {

	private int id;
	private Pessoa pessoa;
	private LocalDate dataAgendamento;
	private StatusAgendamento statusAgendamento;

	public Agendamento(int id, Pessoa pessoa, LocalDate dataAgendamento, StatusAgendamento statusAgendamento) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.dataAgendamento = dataAgendamento;
		this.statusAgendamento = statusAgendamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAgendamento, id, pessoa, statusAgendamento);
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
		return Objects.equals(dataAgendamento, other.dataAgendamento) && id == other.id
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(statusAgendamento, other.statusAgendamento);
	}

}
