package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import com.rlastres.faunagalicia.models.EstructuraAnimal;

public interface EstructuraAnimalService {

	public List<EstructuraAnimal> getAllEstructuras();
	public Optional<EstructuraAnimal> getEstructuraById(int id);
	public Optional<EstructuraAnimal> getEstructuraByName(String nombre_estruct);
	public EstructuraAnimal saveEstructura(EstructuraAnimal e);
	public boolean deleteEstructuraById(int id);
	
}
