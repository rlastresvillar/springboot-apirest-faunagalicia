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

import com.rlastres.faunagalicia.models.GrupoAnimal;
import com.rlastres.faunagalicia.services.GrupoAnimalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name="GrupoAnimalService", description="API de la fauna de Galicia") //Swagger
public class GrupoAnimalController {
	
	@Autowired // Inyectamos el servicio de estructura animal
	GrupoAnimalService grupo_service;
	
	@Operation(summary = "Grupos de animales", description = "Lista de los diferentes grupos de animales", tags = {"GrupoAnimalService"}) //Swagger
	@GetMapping("/grupos")
	public @ResponseBody ResponseEntity<List<GrupoAnimal>> getGrupos(){
		// Utilizamos el servicio de grupo animal para obtener todas los grupos animales.
		List<GrupoAnimal> lista_grupos = grupo_service.getAllGrupos();
		return ResponseEntity.ok(lista_grupos);
	} 
	
	@Operation(summary = "Info del grupo animal", description = "Información del grupo animal", tags = {"GrupoAnimalService"}) //Swagger
	@GetMapping("/grupos/{id}")
	public @ResponseBody ResponseEntity<Optional<GrupoAnimal>> getGrupoById(
			@Parameter(description = "id del grupo", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional<GrupoAnimal> grupo_a = grupo_service.getGrupoById(id);
		if(grupo_a.isPresent())
			return ResponseEntity.ok(grupo_a);
		else
			return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Actualiza grupo", description = "Actualiza la información de un grupo de animales", tags = {"GrupoAnimalService"})
	@PutMapping("/grupos/{id}")
	public @ResponseBody ResponseEntity<GrupoAnimal> updateGrupo(
			@Parameter(description = "id del grupo", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable(value="id") int id,
			@RequestBody GrupoAnimal grupo) {
		if(grupo_service.getGrupoById(id).isPresent()) {
			// El grupo existe en BD.
			GrupoAnimal g = grupo_service.getGrupoById(id).get();
			grupo.setIdGrupo(id);
			g.setGrupo(grupo.getGrupo());
			grupo_service.saveGrupo(g);
			return ResponseEntity.ok(g);
		} else {
			// El grupo no existe en BD.
			return ResponseEntity.notFound().build();
		}		
	}
	
	@Operation(summary = "Crear grupo de animales", description = "Crear un nuevo grupo de animales", tags = {"GrupoAnimalService"})
	@PostMapping("/grupos")
	public @ResponseBody ResponseEntity<Object> addGrupo(
			@Parameter(description = "Nombre del grupo de animales", required = true, example = "Mamíferos", in = ParameterIn.QUERY)
			@RequestParam(name = "nombre_grupo") String nombre_grupo) {
		
		if (grupo_service.getGrupoByName(nombre_grupo).isPresent()) {
			// Grupo existente en BD. No podrá anadirse.
			return ResponseEntity.badRequest().body("El grupo de animales " + nombre_grupo + " ya existe");
		} else {
			GrupoAnimal nuevo_grupo = new GrupoAnimal(nombre_grupo);
			grupo_service.saveGrupo(nuevo_grupo);
		}
		
		// Comprobación creación exitosa en BD.		
		Optional<GrupoAnimal> grupo_creado = grupo_service.getGrupoByName(nombre_grupo);
		if (!grupo_creado.isPresent() || grupo_creado.get() == null) {
			return ResponseEntity.internalServerError().body("No se ha podido crear el grupo " + nombre_grupo);
		} else {
			return ResponseEntity.ok(grupo_creado.get());
		}	
	}
	
	
	@Operation(summary = "Suprimir grupo", description = "Elimina un grupo de animales", tags = {"GrupoAnimalService"})
	@DeleteMapping("/grupos/{id}")
	public @ResponseBody ResponseEntity<Optional<GrupoAnimal>> deleteGrupoById(
			@Parameter(description = "id del grupo", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional <GrupoAnimal> grupo_borrar = grupo_service.getGrupoById(id);
		if(grupo_borrar.isPresent()) {
			if(grupo_service.deleteGrupoById(id))	
				return ResponseEntity.ok(grupo_borrar);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}