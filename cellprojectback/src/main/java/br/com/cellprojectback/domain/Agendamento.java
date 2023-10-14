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
@Table(name = "agendamento")
public class Agendamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "codigo")
	private String codigo;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@Column(name = "data_agendamento")
	private LocalDate dataAgendamento;
	
	@Column(name = "horario_agendamento")
	private String horarioAgendamento;
	
	@ManyToOne
	@JoinColumn(name = "tiposervico_id")
	private TipoServico tipoServico;
	
	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusAgendamento statusAgendamento;

	public Agendamento(int id, String codigo, Pessoa pessoa, LocalDate dataAgendamento, String horarioAgendamento,
			TipoServico tipoServico, StatusAgendamento statusAgendamento) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.pessoa = pessoa;
		this.dataAgendamento = dataAgendamento;
		this.horarioAgendamento = horarioAgendamento;
		this.tipoServico = tipoServico;
		this.statusAgendamento = statusAgendamento;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public String getHorarioAgendamento() {
		return horarioAgendamento;
	}

	public void setHorarioAgendamento(String horarioAgendamento) {
		this.horarioAgendamento = horarioAgendamento;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, dataAgendamento, horarioAgendamento, id, pessoa, statusAgendamento, tipoServico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(dataAgendamento, other.dataAgendamento)
				&& Objects.equals(horarioAgendamento, other.horarioAgendamento) && id == other.id
				&& Objects.equals(pessoa, other.pessoa) && Objects.equals(statusAgendamento, other.statusAgendamento)
				&& Objects.equals(tipoServico, other.tipoServico);
	}

}
