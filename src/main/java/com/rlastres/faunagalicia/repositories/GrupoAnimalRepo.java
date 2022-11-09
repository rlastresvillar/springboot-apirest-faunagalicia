package com.rlastres.faunagalicia.repositories;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rlastres.faunagalicia.models.GrupoAnimal;

@Repository
public interface GrupoAnimalRepo extends CrudRepository<GrupoAnimal,Integer>{ // Clase del modelo y el tipo de la clave primaria	
	public Optional<GrupoAnimal> findByGrupo(String grupo);
}
