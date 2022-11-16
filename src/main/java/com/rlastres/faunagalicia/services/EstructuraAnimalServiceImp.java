package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlastres.faunagalicia.models.EstructuraAnimal;
import com.rlastres.faunagalicia.repositories.EstructuraAnimalRepo;

@Service
public class EstructuraAnimalServiceImp implements EstructuraAnimalService {

	@Autowired //Inyectamos el repositorio
	EstructuraAnimalRepo estructura_repo;
	
	@Override
	public List<EstructuraAnimal> getAllEstructuras() {
		return (List<EstructuraAnimal>) estructura_repo.findAll();
	}

	@Override
	public Optional<EstructuraAnimal> getEstructuraById(int id) {
		return estructura_repo.findById(id);
	}

	@Override
	public EstructuraAnimal saveEstructura(EstructuraAnimal e) {
		return estructura_repo.save(e);
	}

	@Override
	public boolean deleteEstructuraById(int id) {
		try {
			Optional<EstructuraAnimal> estructura = this.getEstructuraById(id);
			estructura_repo.delete(estructura.get());
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	@Override
	public Optional<EstructuraAnimal> getEstructuraByName(String nombre_estruct) {
		return (Optional<EstructuraAnimal>)estructura_repo.findByEstructura(nombre_estruct);
	}

}