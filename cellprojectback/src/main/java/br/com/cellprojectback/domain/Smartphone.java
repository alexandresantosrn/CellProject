package br.com.cellprojectback.domain;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "smartphone")
public class Smartphone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "modelo")
	private String modelo;
	
	@OneToOne
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;

	public Smartphone(int id, String modelo, Fabricante fabricante) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.fabricante = fabricante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fabricante, id, modelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Smartphone other = (Smartphone) obj;
		return Objects.equals(fabricante, other.fabricante) && id == other.id && Objects.equals(modelo, other.modelo);
	}

}
