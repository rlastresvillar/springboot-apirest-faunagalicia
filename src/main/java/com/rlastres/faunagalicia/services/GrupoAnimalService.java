package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import com.rlastres.faunagalicia.models.GrupoAnimal;

public interface GrupoAnimalService {

	public List<GrupoAnimal> getAllGrupos();
	public Optional<GrupoAnimal> getGrupoById(int id);
	public Optional<GrupoAnimal> getGrupoByName(String nombre_grupo);
	public GrupoAnimal saveGrupo(GrupoAnimal e);
	public boolean deleteGrupoById(int id);
	
}
