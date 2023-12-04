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
@Table(name = "requisicaopecas")
public class RequisicaoPecas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "ordem_id")
	private OrdemServico ordemServico;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "data_solicitacao")
	private LocalDate dataSolicitacao;

	@Column(name = "data_autorizacao")
	private LocalDate dataAutorizacao;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusRequisicao statusRequisicao;

	public RequisicaoPecas() {
		super();
	}

	public RequisicaoPecas(int id, OrdemServico ordemServico, Produto produto, int quantidade,
			LocalDate dataSolicitacao, LocalDate dataAutorizacao, StatusRequisicao statusRequisicao) {
		super();
		this.id = id;
		this.ordemServico = ordemServico;
		this.produto = produto;
		this.quantidade = quantidade;
		this.dataSolicitacao = dataSolicitacao;
		this.dataAutorizacao = dataAutorizacao;
		this.statusRequisicao = statusRequisicao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(LocalDate dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public LocalDate getDataAutorizacao() {
		return dataAutorizacao;
	}

	public void setDataAutorizacao(LocalDate dataAutorizacao) {
		this.dataAutorizacao = dataAutorizacao;
	}

	public StatusRequisicao getStatusRequisicao() {
		return statusRequisicao;
	}

	public void setStatusRequisicao(StatusRequisicao statusRequisicao) {
		this.statusRequisicao = statusRequisicao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataAutorizacao, dataSolicitacao, id, ordemServico, produto, quantidade, statusRequisicao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequisicaoPecas other = (RequisicaoPecas) obj;
		return Objects.equals(dataAutorizacao, other.dataAutorizacao)
				&& Objects.equals(dataSolicitacao, other.dataSolicitacao) && id == other.id
				&& Objects.equals(ordemServico, other.ordemServico) && Objects.equals(produto, other.produto)
				&& quantidade == other.quantidade && Objects.equals(statusRequisicao, other.statusRequisicao);
	}

}
