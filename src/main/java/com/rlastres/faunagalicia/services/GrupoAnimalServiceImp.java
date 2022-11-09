package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlastres.faunagalicia.models.GrupoAnimal;
import com.rlastres.faunagalicia.repositories.GrupoAnimalRepo;

@Service
public class GrupoAnimalServiceImp implements GrupoAnimalService {

	@Autowired //Inyectamos el repositorio
	GrupoAnimalRepo grupo_repo;
	
	@Override
	public List<GrupoAnimal> getAllGrupos() {
		return (List<GrupoAnimal>)grupo_repo.findAll();
	}

	@Override
	public Optional<GrupoAnimal> getGrupoById(int id) {
		return (Optional<GrupoAnimal>)grupo_repo.findById(id);
	}

	@Override
	public GrupoAnimal saveGrupo(GrupoAnimal g) {
		return grupo_repo.save(g);
	}

	@Override
	public boolean deleteGrupoById(int id) {
		try {
			Optional<GrupoAnimal> grupo = this.getGrupoById(id);
			grupo_repo.delete(grupo.get());
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public Optional<GrupoAnimal> getGrupoByName(String nombre_grupo) {
		return (Optional<GrupoAnimal>)grupo_repo.findByGrupo(nombre_grupo);
	}

}