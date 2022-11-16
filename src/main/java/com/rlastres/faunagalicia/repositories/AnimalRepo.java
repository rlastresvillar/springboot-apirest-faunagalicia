package com.rlastres.faunagalicia.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rlastres.faunagalicia.models.Animal;

@Repository
public interface AnimalRepo extends CrudRepository<Animal,Integer>{ // Clase del modelo y el tipo de la clave primaria	
	public Optional<Animal> findByEspecie(String especie);
	public Optional<Animal> findByNombreVulgar(String nombre_vulgar);
}
