package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import com.rlastres.faunagalicia.models.Animal;

public interface AnimalService {
	
	public List<Animal> getAllAnimales();
	public Optional<Animal> getAnimalById(int id);
	public Animal saveAnimal(Animal a);
	public boolean deleteAnimalById(int id);
	
}
