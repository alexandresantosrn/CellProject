package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.StatusReparo;

public class StatusReparoRepository {
	
	private static List<StatusReparo> tiposReparo = new ArrayList<StatusReparo>();

	public static List<StatusReparo> getStatusReparo() {
		return tiposReparo;
	}

	public static StatusReparo addStatusReparo(StatusReparo status) {
		tiposReparo.add(status);
		return status;
	}

}
