package com.rlastres.faunagalicia.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rlastres.faunagalicia.models.Animal;
import com.rlastres.faunagalicia.repositories.EstadoConservacionRepo;
import com.rlastres.faunagalicia.repositories.EstructuraAnimalRepo;
import com.rlastres.faunagalicia.repositories.GrupoAnimalRepo;
import com.rlastres.faunagalicia.services.AnimalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name="AnimalService", description="API de la fauna de Galicia") //Swagger
public class AnimalController {
	
	@Autowired // Inyectamos el servicio
	AnimalService animal_service;
	
	@Autowired
	GrupoAnimalRepo grupo_repo;
	@Autowired
	EstadoConservacionRepo estado_repo;
	@Autowired
	EstructuraAnimalRepo estructura_repo;
	
	@Operation(summary = "Animales de Galicia", description = "Lista de los animales presentes en Galicia", tags = {"AnimalService"}) //Swagger
	@GetMapping("/animales")
	public @ResponseBody ResponseEntity<List<Animal>> getAnimales(){
		// Utilizamos el servicio de estado de conservacion para obtener todos los posibles estados.
		List<Animal> lista_animales = animal_service.getAllAnimales();
		return ResponseEntity.ok(lista_animales);
	} 
	
	@Operation(summary = "Info sobre el animal", description = "Información detallada sobre el animal", tags = {"AnimalService"}) //Swagger
	@GetMapping("/animales/{id}")
	public @ResponseBody ResponseEntity<Optional<Animal>> getAnimalById(
			@Parameter(description = "id del animal", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		System.out.println("Get animal por id");
		Optional<Animal> animal  = animal_service.getAnimalById(id);
		System.out.println("Get animal por id FIN");
		if(animal.isPresent())
			return ResponseEntity.ok(animal);
		else
			return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Actualiza un animal", description = "Actualiza la información relativa a un animal", tags = {"AnimalService"})
	@PutMapping("/animales/{id}")
	public @ResponseBody ResponseEntity<Animal> updateAnimal(
			@Parameter(description = "id del animal", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable(value="id") int id,
			@RequestBody @Valid Animal animal) {
		if(animal_service.getAnimalById(id).isPresent()) {
			// El animal existe en BD.
			Animal a = animal_service.getAnimalById(id).get();
			animal.setIdAnimal(id);
			a.setDistribucion(animal.getDistribucion());
			a.setEspecie(animal.getEspecie());
			a.setNombreVulgar(animal.getNombreVulgar());
			a.setOtrasDenominaciones(animal.getOtrasDenominaciones());
			a.setEstadoConservacion(estado_repo.findByEstado(animal.getEstadoConservacion().getEstado()).get());
			a.setEstructuraAnimal(estructura_repo.findByEstructura(animal.getEstructuraAnimal().getEstructura()).get());
			a.setGrupoAnimal(grupo_repo.findByGrupo(animal.getGrupoAnimal().getGrupo()).get());
			animal_service.saveAnimal(a);
			return ResponseEntity.ok(a);
		} else {
			// El animal no existe en BD.
			return ResponseEntity.notFound().build();
		}		
	}
	
	@Operation(summary = "Crear un nuevo animal", description = "Añadir un nuevo animal a la lista de animales de FaunaGalicia", tags = {"AnimalService"})
	@PostMapping(path="/animales", produces = "application/json")
	public @ResponseBody ResponseEntity<Object> addAnimal(
			@RequestBody @Valid Animal animal) {

		if(animal_service.getAnimalByEspecie(animal.getEspecie()).isPresent())
			return ResponseEntity.badRequest().body("El animal cuya especie es '" + animal.getEspecie() + "' ya existe");
		else if(animal_service.getAnimalByNombreVulgar(animal.getNombreVulgar()).isPresent())
			return ResponseEntity.badRequest().body("El animal cuyo nombre vulgar es '" + animal.getNombreVulgar() + "' ya existe");
		else {
			if(!estado_repo.findByEstado(animal.getEstadoConservacion().getEstado()).isPresent())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El estado de conservación '" + animal.getEstadoConservacion().getEstado() + "' no existe. Debes crearlo antes de agregar el animal.");
			if(!estructura_repo.findByEstructura(animal.getEstructuraAnimal().getEstructura()).isPresent())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La estructura '" + animal.getEstructuraAnimal().getEstructura() + "' no existe. Debes crearla antes de agregar el animal.");
			if(!grupo_repo.findByGrupo(animal.getGrupoAnimal().getGrupo()).isPresent())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El grupo de animales '" + animal.getGrupoAnimal().getGrupo() + "' no existe. Debes crearlo antes de agregar el animal.");

			Animal a = new Animal();
			a.setDistribucion(animal.getDistribucion());
			a.setEspecie(animal.getEspecie());
			a.setNombreVulgar(animal.getNombreVulgar());
			a.setOtrasDenominaciones(animal.getOtrasDenominaciones());
			a.setEstadoConservacion(estado_repo.findByEstado(animal.getEstadoConservacion().getEstado()).get());
			a.setEstructuraAnimal(estructura_repo.findByEstructura(animal.getEstructuraAnimal().getEstructura()).get());
			a.setGrupoAnimal(grupo_repo.findByGrupo(animal.getGrupoAnimal().getGrupo()).get());
			animal_service.saveAnimal(a);
			
			Optional<Animal> animal_creado = animal_service.getAnimalByEspecie(animal.getEspecie());
			if(animal_creado.isPresent())
				return ResponseEntity.ok(animal_creado.get());
			else
				return ResponseEntity.internalServerError().body("No se ha podido crear el animal '" + animal_creado.get().getEspecie() + "'");
		}	
	}
	
	
	@Operation(summary = "Suprimir animal", description = "Elimina un animal", tags = {"AnimalService"})
	@DeleteMapping("/animales/{id}")
	public @ResponseBody ResponseEntity<Optional<Animal>> deleteAnimalById(
			@Parameter(description = "id del animal", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional <Animal> animal_borrar = animal_service.getAnimalById(id);
		if(animal_borrar.isPresent()) {
			if(animal_service.deleteAnimalById(id))	
				return ResponseEntity.ok(animal_borrar);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}