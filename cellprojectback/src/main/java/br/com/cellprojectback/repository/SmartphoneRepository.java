package br.com.cellprojectback.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.cellprojectback.domain.Smartphone;

public class SmartphoneRepository {

	private static List<Smartphone> smartphones = new ArrayList<Smartphone>();

	public static List<Smartphone> getSmartphones() {
		return smartphones;
	}

	public static Smartphone addSmartphone(Smartphone smartphone) {
		smartphones.add(smartphone);
		return smartphone;
	}

}
