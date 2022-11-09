package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import com.rlastres.faunagalicia.models.EstadoConservacion;

public interface EstadoConservacionService {
	
	public List<EstadoConservacion> getAllEstados();
	public Optional<EstadoConservacion> getEstadoById(int id);
	public Optional<EstadoConservacion> getEstadoByCodigo(String codigo);
	public Optional<EstadoConservacion> getEstadoByName(String estado);
	public EstadoConservacion saveEstado(EstadoConservacion e);
	public boolean deleteEstadoById(int id);
	
}
