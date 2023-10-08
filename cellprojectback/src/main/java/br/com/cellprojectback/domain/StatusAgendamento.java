package br.com.cellprojectback.domain;

import java.util.Objects;

public class StatusAgendamento {

	private int id;
	private String descricao;

	public StatusAgendamento(int id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusAgendamento other = (StatusAgendamento) obj;
		return Objects.equals(descricao, other.descricao) && id == other.id;
	}

}
