package br.com.cellprojectback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cellprojectback.domain.Smartphone;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer>{

}
