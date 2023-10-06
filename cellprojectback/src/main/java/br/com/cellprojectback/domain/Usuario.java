package br.com.cellprojectback.domain;

import java.util.Date;
import java.util.Objects;

public class Usuario {

	private int id;
	Pessoa pessoa;
	private Date dataCadastro;
	boolean autorizado;

	public Usuario(int id, Pessoa pessoa, Date dataCadastro, boolean autorizado) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.dataCadastro = dataCadastro;
		this.autorizado = autorizado;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autorizado, dataCadastro, id, pessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return autorizado == other.autorizado && Objects.equals(dataCadastro, other.dataCadastro) && id == other.id
				&& Objects.equals(pessoa, other.pessoa);
	}

}
