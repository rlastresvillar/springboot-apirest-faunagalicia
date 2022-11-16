package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import com.rlastres.faunagalicia.models.Animal;

public interface AnimalService {
	
	public List<Animal> getAllAnimales();
	public Optional<Animal> getAnimalById(int id);
	public Optional<Animal> getAnimalByEspecie(String especie);
	public Optional<Animal> getAnimalByNombreVulgar(String nombre_vulgar);
	public Animal saveAnimal(Animal a);
	public boolean deleteAnimalById(int id);
	
}
