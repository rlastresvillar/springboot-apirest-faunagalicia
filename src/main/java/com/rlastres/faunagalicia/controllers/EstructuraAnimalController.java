package com.rlastres.faunagalicia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public @ResponseBody ResponseEntity<List<EstructuraAnimal>> getEstructuras(){
		return ResponseEntity.ok(estructura_service.getAllEstructuras());
	}
	
	@Operation(summary = "Info de la estructura", description = "Información de la estructura del animal", tags = {"EstructuraAnimalService"}) //Swagger
	@GetMapping("/estructuras/{id}")
	public @ResponseBody ResponseEntity<Optional<EstructuraAnimal>> getEstructuraById(
			@Parameter(description = "id de la estructura", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional<EstructuraAnimal> estruct = estructura_service.getEstructuraById(id);
		if(estruct.isPresent())
			return ResponseEntity.ok(estruct);
		else
			return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Actualiza estructura", description = "Actualiza la información de una estructura animal", tags = {"EstructuraAnimalService"})
	@PutMapping("/estructuras/{id}")
	public @ResponseBody ResponseEntity<EstructuraAnimal> updateEstructura(
			@Parameter(description = "id de la estructura", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable(value="id") int id,
			@RequestBody EstructuraAnimal estruct) {
		if(estructura_service.getEstructuraById(id).isPresent()) {
			EstructuraAnimal e = estructura_service.getEstructuraById(id).get();
			estruct.setIdEstructura(id);
			e.setEstructura(estruct.getEstructura());
			estructura_service.saveEstructura(e);
			return ResponseEntity.ok(e);
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@Operation(summary = "Crear estructura animal", description = "Crear una nueva estructura de animales", tags = {"EstructuraAnimalService"})
	@PostMapping("/estructuras")
	public @ResponseBody ResponseEntity<Object> addEstructura(
			@Parameter(description = "Nombre de la estructura animal", required = true, example = "Invertebrados", in = ParameterIn.QUERY)
			@RequestParam(name = "nombre_estruct") String nombre_estruct) {
		
		if (estructura_service.getEstructuraByName(nombre_estruct).isPresent()) {
			return ResponseEntity.badRequest().body("La estructura " + nombre_estruct + " ya existe");
		} else {
			EstructuraAnimal nueva_estruc = new EstructuraAnimal(nombre_estruct);
			estructura_service.saveEstructura(nueva_estruc);
		}
		
		// Comprobación creación exitosa en BD.		
		Optional<EstructuraAnimal> estruct_creada = estructura_service.getEstructuraByName(nombre_estruct);
		if (!estruct_creada.isPresent() || estruct_creada.get() == null) {
			return ResponseEntity.internalServerError().body("No se ha podido crear la estructura " + nombre_estruct);
		} else {
			return ResponseEntity.ok(estruct_creada.get());
		}	
	}
	
	@Operation(summary = "Suprimir estructura", description = "Elimina una estructura animal", tags = {"EstructuraAnimalService"})
	@DeleteMapping("/estructuras/{id}")
	public @ResponseBody ResponseEntity<Optional<EstructuraAnimal>> deleteEstructuraById(
			@Parameter(description = "id de la estructura", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional <EstructuraAnimal> estruct_borrar = estructura_service.getEstructuraById(id);
		if(estruct_borrar.isPresent()) {
			if(estructura_service.deleteEstructuraById(id))
				return ResponseEntity.ok(estruct_borrar);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}