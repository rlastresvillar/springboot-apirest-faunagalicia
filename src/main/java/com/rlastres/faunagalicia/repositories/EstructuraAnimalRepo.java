package com.rlastres.faunagalicia.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rlastres.faunagalicia.models.EstructuraAnimal;

@Repository
public interface EstructuraAnimalRepo extends CrudRepository<EstructuraAnimal, Integer>{ // Clase del modelo y el tipo de la clave primaria	
	public Optional<EstructuraAnimal> findByEstructura(String estruct);
}
