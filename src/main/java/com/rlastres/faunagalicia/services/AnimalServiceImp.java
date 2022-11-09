package com.rlastres.faunagalicia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlastres.faunagalicia.models.Animal;
import com.rlastres.faunagalicia.repositories.AnimalRepo;

@Service
public class AnimalServiceImp implements AnimalService{

	@Autowired // Inyectamos repositorio
	AnimalRepo animal_repo;
	
	@Override
	public List<Animal> getAllAnimales() {
		return (List<Animal>)animal_repo.findAll();
	}

	@Override
	public Optional<Animal> getAnimalById(int id) {
		return (Optional<Animal>)animal_repo.findById(id);
	}

	@Override
	public Animal saveAnimal(Animal a) {
		return animal_repo.save(a);
	}

	@Override
	public boolean deleteAnimalById(int id) {
		try {
			Optional<Animal> animal = this.getAnimalById(id);
			animal_repo.delete(animal.get());
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	
}