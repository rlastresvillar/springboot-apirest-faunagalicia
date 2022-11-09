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

import com.rlastres.faunagalicia.models.EstadoConservacion;
import com.rlastres.faunagalicia.services.EstadoConservacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/")
@Tag(name="EstadoConservacionService", description="API de la fauna de Galicia") //Swagger
public class EstadoConservacionController {
	
	@Autowired // Inyectamos el servicio de estructura animal
	EstadoConservacionService estado_service;
	
	@Operation(summary = "Estados de conservacion", description = "Lista de los posibles estados de conservación de los animales", tags = {"EstadoConservacionService"}) //Swagger
	@GetMapping("/estados")
	public @ResponseBody ResponseEntity<List<EstadoConservacion>> getEstados(){
		// Utilizamos el servicio de estado de conservacion para obtener todos los posibles estados.
		List<EstadoConservacion> lista_estados = estado_service.getAllEstados();
		return ResponseEntity.ok(lista_estados);
	} 
	
	@Operation(summary = "Info del estado de conservación", description = "Información del estado de conservación", tags = {"EstadoConservacionService"}) //Swagger
	@GetMapping("/estados/{id}")
	public @ResponseBody ResponseEntity<Optional<EstadoConservacion>> getEstadoById(
			@Parameter(description = "id del estado", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional<EstadoConservacion> estado_c  = estado_service.getEstadoById(id);
		if(estado_c.isPresent())
			return ResponseEntity.ok(estado_c);
		else
			return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Actualiza estado", description = "Actualiza la información de un estado de conservación", tags = {"EstadoConservacionService"})
	@PutMapping("/estados/{id}")
	public @ResponseBody ResponseEntity<EstadoConservacion> updateEstado(
			@Parameter(description = "id del estado", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable(value="id") int id,
			@RequestBody EstadoConservacion estado) {
		if(estado_service.getEstadoById(id).isPresent()) {
			// El grupo existe en BD.
			EstadoConservacion e = estado_service.getEstadoById(id).get();
			estado.setIdEstado(id);
			e.setEstado(estado.getEstado());
			e.setCodigo(estado.getCodigo());
			estado_service.saveEstado(e);
			return ResponseEntity.ok(e);
		} else {
			// El grupo no existe en BD.
			return ResponseEntity.notFound().build();
		}		
	}
	
	@Operation(summary = "Crear un estado de conservación", description = "Crear un nuevo estado de conservación", tags = {"EstadoConservacionService"})
	@PostMapping("/estados")
	public @ResponseBody ResponseEntity<Object> addGrupo(
			@Parameter(description = "Código del estado de conservación", required = true, example = "EX", in = ParameterIn.QUERY)
			@RequestParam(name = "codigo_estado") String codigo_estado,
			@Parameter(description = "Nombre del estado de conservación", required = true, example = "En peligro crítico", in = ParameterIn.QUERY)
			@RequestParam(name = "nombre_estado") String nombre_estado){
		
		if (estado_service.getEstadoByCodigo(codigo_estado).isPresent() || estado_service.getEstadoByName(nombre_estado).isPresent()) {
			// Grupo existente en BD. No podrá anadirse.
			return ResponseEntity.badRequest().body("El estado de conservación con código " + codigo_estado + " y nombre " + nombre_estado + " ya existe");
		} else {
			EstadoConservacion nuevo_estado = new EstadoConservacion(codigo_estado, nombre_estado);
			estado_service.saveEstado(nuevo_estado);
		}
		
		// Comprobación creación exitosa en BD.		
		Optional<EstadoConservacion> estado_creado = estado_service.getEstadoByName(nombre_estado);
		if (!estado_creado.isPresent() || estado_creado.get() == null) {
			return ResponseEntity.internalServerError().body("No se ha podido crear el estado de conservación " + nombre_estado + " con código " + codigo_estado);
		} else {
			return ResponseEntity.ok(estado_creado.get());
		}	
	}
	
	
	@Operation(summary = "Suprimir estado de conservación", description = "Elimina un estado de conservación", tags = {"EstadoConservacionService"})
	@DeleteMapping("/estados/{id}")
	public @ResponseBody ResponseEntity<Optional<EstadoConservacion>> deleteGrupoById(
			@Parameter(description = "id del estado", required = true, example = "1", in = ParameterIn.PATH) //Swagger
			@PathVariable("id") int id) {
		Optional <EstadoConservacion> estado_borrar = estado_service.getEstadoById(id);
		if(estado_borrar.isPresent()) {
			if(estado_service.deleteEstadoById(id))	
				return ResponseEntity.ok(estado_borrar);
			else
				return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}