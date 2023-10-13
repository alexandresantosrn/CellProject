package br.com.cellprojectback.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiposervico")
public class TipoServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String descricao;

	public TipoServico() {
		super();
	}

	public TipoServico(int id) {
		super();

		if (id == 1) {
			this.descricao = "Conserto em Garantia";
		}

		else if (id == 2) {
			this.descricao = "Conserto Fora de Garantia";
		}

		this.id = id;
	}

	public TipoServico(int id, String descricao) {
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
		TipoServico other = (TipoServico) obj;
		return Objects.equals(descricao, other.descricao) && id == other.id;
	}

}
