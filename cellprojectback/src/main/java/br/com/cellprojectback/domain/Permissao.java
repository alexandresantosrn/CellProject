package br.com.cellprojectback.domain;

import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "permissao")
public class Permissao {

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "papel_id")
	private Papel papel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_permissao")
	private Date dataPermissao;

	public Permissao() {
		super();
	}

	public Permissao(int id, Usuario usuario, Papel papel, Date dataPermissao) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.papel = papel;
		this.dataPermissao = dataPermissao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public Date getDataPermissao() {
		return dataPermissao;
	}

	public void setDataPermissao(Date dataPermissao) {
		this.dataPermissao = dataPermissao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataPermissao, id, papel, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permissao other = (Permissao) obj;
		return Objects.equals(dataPermissao, other.dataPermissao) && id == other.id
				&& Objects.equals(papel, other.papel) && Objects.equals(usuario, other.usuario);
	}

}
