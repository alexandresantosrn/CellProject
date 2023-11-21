package br.com.cellprojectback.domain;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordemservico")
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "data_entrada")
	private LocalDate dataEntrada;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;

	@ManyToOne
	@JoinColumn(name = "smartphone_id")
	private Smartphone smartphone;

	@Column(name = "imei")
	private String imei;

	@ManyToOne
	@JoinColumn(name = "tipoServico_id")
	private TipoServico tipoServico;

	@ManyToOne
	@JoinColumn(name = "status_reparo")
	private StatusReparo statusReparo;

	@Column(name = "preco_total")
	private Double precoTotal;

	public OrdemServico() {
		super();
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

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
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
		return Objects.hash(codigo, dataEntrada, id, imei, pessoa, precoTotal, smartphone, statusReparo, tipoServico);
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
		return Objects.equals(codigo, other.codigo) && Objects.equals(dataEntrada, other.dataEntrada) && id == other.id
				&& Objects.equals(imei, other.imei) && Objects.equals(pessoa, other.pessoa)
				&& Objects.equals(precoTotal, other.precoTotal) && Objects.equals(smartphone, other.smartphone)
				&& Objects.equals(statusReparo, other.statusReparo) && Objects.equals(tipoServico, other.tipoServico);
	}

}
