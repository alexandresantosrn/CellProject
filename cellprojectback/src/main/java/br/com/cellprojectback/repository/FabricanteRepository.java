package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Fabricante;

public class FabricanteRepository {
	
	private static List<Fabricante> fabricantes = new ArrayList<Fabricante>();

	public static List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public static Fabricante addFabricante(Fabricante fabricante) {
		fabricantes.add(fabricante);
		return fabricante;
	}
}
