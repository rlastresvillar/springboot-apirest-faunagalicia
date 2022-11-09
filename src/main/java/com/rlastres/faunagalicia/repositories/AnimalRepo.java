package com.rlastres.faunagalicia.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import com.rlastres.faunagalicia.models.Animal;

@Repository
public interface AnimalRepo extends CrudRepository<Animal,Integer>{ // Clase del modelo y el tipo de la clave primaria	

}
