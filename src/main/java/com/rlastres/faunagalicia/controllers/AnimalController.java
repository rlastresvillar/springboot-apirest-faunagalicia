package com.rlastres.faunagalicia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rlastres.faunagalicia.models.Animal;
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
		Optional<Animal> animal  = animal_service.getAnimalById(id);
		if(animal.isPresent())
			return ResponseEntity.ok(animal);
		else
			return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Actualiza un animal", description = "Actualiza la información relativa a un animal", tags = {"AnimalService"})
	@PutMapping("/animales/{id}")
	public @ResponseBody ResponseEntity<Animal> updateEstado(
			@Parameter(description = "id del animal", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable(value="id") int id,
			@RequestBody Animal animal) {
		if(animal_service.getAnimalById(id).isPresent()) {
			// El grupo existe en BD.
			Animal a = animal_service.getAnimalById(id).get();
			animal.setIdAnimal(id);
			a.setDistribucion(animal.getDistribucion());
			a.setEspecie(animal.getEspecie());
			a.setNombreVulgar(animal.getNombreVulgar());
			a.setOtrasDenominaciones(animal.getOtrasDenominaciones());
			a.setEstadoConservacion(animal.getEstadoConservacion());
			a.setEstructuraAnimal(animal.getEstructuraAnimal());
			a.setGrupoAnimal(animal.getGrupoAnimal());
			animal_service.saveAnimal(a);
			return ResponseEntity.ok(a);
		} else {
			// El grupo no existe en BD.
			return ResponseEntity.notFound().build();
		}		
	}
	
//	@Operation(summary = "Crear un nuevo animal", description = "Añadir un nuevo animal", tags = {"AnimalService"})
//	@PostMapping("/animales")
//	public @ResponseBody ResponseEntity<Object> addGrupo(
//			@Parameter(description = "Código del estado de conservación", required = true, example = "EX", in = ParameterIn.QUERY)
//			@RequestParam(name = "codigo_estado") String codigo_estado,
//			@Parameter(description = "Nombre del estado de conservación", required = true, example = "En peligro crítico", in = ParameterIn.QUERY)
//			@RequestParam(name = "nombre_estado") String nombre_estado){
//		
//		if (animal_service.getEstadoByCodigo(codigo_estado).isPresent() || animal_service.getEstadoByName(nombre_estado).isPresent()) {
//			// Grupo existente en BD. No podrá anadirse.
//			return ResponseEntity.badRequest().body("El estado de conservación con código " + codigo_estado + " y nombre " + nombre_estado + " ya existe");
//		} else {
//			EstadoConservacion nuevo_estado = new EstadoConservacion(codigo_estado, nombre_estado);
//			animal_service.saveEstado(nuevo_estado);
//		}
//		
//		// Comprobación creación exitosa en BD.		
//		Optional<EstadoConservacion> estado_creado = estado_service.getEstadoByName(nombre_estado);
//		if (!estado_creado.isPresent() || estado_creado.get() == null) {
//			return ResponseEntity.internalServerError().body("No se ha podido crear el estado de conservación " + nombre_estado + " con código " + codigo_estado);
//		} else {
//			return ResponseEntity.ok(estado_creado.get());
//		}	
//	}
	
	
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