package br.com.cellprojectback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cellprojectback.util.Data;

@SpringBootApplication
public class CellprojectbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CellprojectbackApplication.class, args);
		Data.carregarDados();
	}

}
