package br.com.cellprojectback.domain;

import java.time.LocalDate;
import java.util.Objects;

public class OrdemServico {

	private int id;
	private String codigo;
	private LocalDate dataCadastro;
	private Pessoa pessoa;
	private Smartphone smartphone;
	private TipoServico tipoServico;
	private StatusReparo statusReparo;
	private Double precoTotal;

	public OrdemServico(int id, String codigo, LocalDate dataCadastro, Pessoa pessoa, Smartphone smartphone,
			TipoServico tipoServico, StatusReparo statusReparo, Double precoTotal) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.dataCadastro = dataCadastro;
		this.pessoa = pessoa;
		this.smartphone = smartphone;
		this.tipoServico = tipoServico;
		this.statusReparo = statusReparo;
		this.precoTotal = precoTotal;
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

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Smartphone getSmartphone() {
		return smartphone;
	}

	public void setSmartphone(Smartphone smartphone) {
		this.smartphone = smartphone;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public StatusReparo getStatusReparo() {
		return statusReparo;
	}

	public void setStatusReparo(StatusReparo statusReparo) {
		this.statusReparo = statusReparo;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataCadastro, id, pessoa, precoTotal, smartphone, statusReparo, tipoServico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(dataCadastro, other.dataCadastro)
				&& id == other.id && Objects.equals(pessoa, other.pessoa)
				&& Objects.equals(precoTotal, other.precoTotal) && Objects.equals(smartphone, other.smartphone)
				&& Objects.equals(statusReparo, other.statusReparo) && Objects.equals(tipoServico, other.tipoServico);
	}

}
