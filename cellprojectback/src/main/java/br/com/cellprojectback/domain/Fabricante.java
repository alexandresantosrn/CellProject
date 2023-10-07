package br.com.cellprojectback.domain;

import java.util.Objects;

public class Fabricante {

	private int id;
	String denominacao;

	public Fabricante(int id, String denominacao) {
		super();
		this.id = id;
		this.denominacao = denominacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(denominacao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fabricante other = (Fabricante) obj;
		return Objects.equals(denominacao, other.denominacao) && id == other.id;
	}

}
