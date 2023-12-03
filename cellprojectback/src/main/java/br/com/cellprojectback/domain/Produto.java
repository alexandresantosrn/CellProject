package br.com.cellprojectback.domain;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "codigo")
	private int codigo;

	@Column(name = "data_entrada")
	private LocalDate dataEntrada;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "denominacao")
	private String denominacao;

	public Produto() {
		super();
	}

	public Produto(int id) {
		super();
		this.id = id;
	}

	public Produto(int id, int codigo, LocalDate dataEntrada, int quantidade, String denominacao) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.dataEntrada = dataEntrada;
		this.quantidade = quantidade;
		this.denominacao = denominacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataEntrada, denominacao, id, quantidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return codigo == other.codigo && Objects.equals(dataEntrada, other.dataEntrada)
				&& Objects.equals(denominacao, other.denominacao) && id == other.id && quantidade == other.quantidade;
	}

}
