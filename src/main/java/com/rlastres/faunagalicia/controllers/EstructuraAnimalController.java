package com.rlastres.faunagalicia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rlastres.faunagalicia.models.EstructuraAnimal;
import com.rlastres.faunagalicia.services.EstructuraAnimalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name="EstructuraAnimalService", description="API de la fauna de Galicia") //Swagger
public class EstructuraAnimalController {
	
	@Autowired // Inyectamos el servicio de estructura animal
	EstructuraAnimalService estructura_service;
	
	@Operation(summary = "Estructuras animales", description = "Lista de las estructuras de los animales", tags = {"EstructuraAnimalService"}) //Swagger
	@GetMapping("/estructuras")
	public List<EstructuraAnimal> getEstructuras(){
		// Utilizamos el servicio de estructura animal para obtener todas las estructuras animales.
		return estructura_service.getAllEstructuras();
	}
	
	@Operation(summary = "Info de la estructura", description = "Información de la estructura del animal", tags = {"EstructuraAnimalService"}) //Swagger
	@GetMapping("/estructuras/{id}")
	public Optional<EstructuraAnimal> getEstructuraById(
			@Parameter(description = "id de la estructura", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		return estructura_service.getEstructuraById(id);
	}
	
	@Operation(summary = "Crear o modificar estructura", description = "Crear una nueva estructura o actualiza una existente", tags = {"EstructuraAnimalService"})
	@PostMapping("/estructuras/{id}")
	public EstructuraAnimal saveEstructura(
			@Parameter(description = "id de la estructura", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@RequestBody EstructuraAnimal e) {
		return estructura_service.saveEstructura(e);
	}
	
	@Operation(summary = "Suprimir estructura", description = "Elimina una estructura animal", tags = {"EstructuraAnimalService"})
	@DeleteMapping("/estructuras/{id}")
	public String deleteEstructuraById(
			@Parameter(description = "id de la estructura", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		if(estructura_service.deleteEstructuraById(id))
			return "Se ha eliminado correctamente la estructura animal";
		else
			return "No se ha podido eliminar la estructura animal";
	}
	
}