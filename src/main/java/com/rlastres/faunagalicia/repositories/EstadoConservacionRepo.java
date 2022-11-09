package com.rlastres.faunagalicia.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rlastres.faunagalicia.models.EstadoConservacion;

public interface EstadoConservacionRepo extends CrudRepository<EstadoConservacion, Integer>{ // Clase del modelo y el tipo de la clave primaria	
	public Optional<EstadoConservacion> findByCodigo(String codigo);
	public Optional<EstadoConservacion> findByEstado(String grupo);
}
