package br.com.cellprojectback.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OrdemServico {

	private int id;
	private String codigo;
	private Date dataCadastro;
	private Pessoa pessoa;
	private Smartphone smartphone;
	private TipoServico tipoServico;
	private BigDecimal precoTotal;

	public OrdemServico(int id, String codigo, Date dataCadastro, Pessoa pessoa, Smartphone smartphone,
			TipoServico tipoServico, BigDecimal precoTotal) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.dataCadastro = dataCadastro;
		this.pessoa = pessoa;
		this.smartphone = smartphone;
		this.tipoServico = tipoServico;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
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

	public BigDecimal getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(BigDecimal precoTotal) {
		this.precoTotal = precoTotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataCadastro, id, pessoa, precoTotal, smartphone, tipoServico);
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
				&& Objects.equals(tipoServico, other.tipoServico);
	}

}
