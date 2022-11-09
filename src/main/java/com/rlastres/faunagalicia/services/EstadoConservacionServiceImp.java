package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlastres.faunagalicia.models.EstadoConservacion;
import com.rlastres.faunagalicia.repositories.EstadoConservacionRepo;

@Service
public class EstadoConservacionServiceImp implements EstadoConservacionService {

	@Autowired //Inyectamos el repositorio
	EstadoConservacionRepo estado_repo;
	
	@Override
	public List<EstadoConservacion> getAllEstados() {
		return (List<EstadoConservacion>)estado_repo.findAll();
	}

	@Override
	public Optional<EstadoConservacion> getEstadoById(int id) {
		return (Optional<EstadoConservacion>)estado_repo.findById(id);
	}

	@Override
	public Optional<EstadoConservacion> getEstadoByCodigo(String codigo) {
		return (Optional<EstadoConservacion>)estado_repo.findByCodigo(codigo);
	}

	@Override
	public Optional<EstadoConservacion> getEstadoByName(String estado) {
		return (Optional<EstadoConservacion>)estado_repo.findByEstado(estado);
	}

	@Override
	public EstadoConservacion saveEstado(EstadoConservacion e) {
		return estado_repo.save(e);
	}

	@Override
	public boolean deleteEstadoById(int id) {
		try {
			Optional<EstadoConservacion> estado = this.getEstadoById(id);
			estado_repo.delete(estado.get());
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

}