package br.com.cellprojectback.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Usuario {

	private int id;
	String usuario;
	String senha;
	Pessoa pessoa;
	private LocalDate dataCadastro;
	boolean autorizado;

	public Usuario(int id, String usuario, String senha, Pessoa pessoa, LocalDate dataCadastro, boolean autorizado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
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
		return Objects.hash(autorizado, dataCadastro, id, pessoa, senha, usuario);
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
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(senha, other.senha)
				&& Objects.equals(usuario, other.usuario);
	}

}
