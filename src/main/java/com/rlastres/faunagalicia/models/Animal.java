package com.rlastres.faunagalicia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="animales")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_animal;
	
	private String especie;
	private String nombre_vulgar;
	private String otras_denom;
	private String distribucion;
	
	@ManyToOne()
    @JoinColumn(name = "id_estado")
	private EstadoConservacion estado_conservacion;
	
	@ManyToOne()
    @JoinColumn(name = "id_grupo")
	private GrupoAnimal grupo_animal;
	
	@ManyToOne()
    @JoinColumn(name = "id_estructura")
	private EstructuraAnimal estructura_animal;
	
	public Animal() {}

	public int getIdAnimal() {
		return id_animal;
	}

	public String getEspecie() {
		return especie;
	}

	public String getNombreVulgar() {
		return nombre_vulgar;
	}

	public String getOtrasDenominaciones() {
		return otras_denom;
	}

	public String getDistribucion() {
		return distribucion;
	}

	public EstadoConservacion getEstadoConservacion() {
		return estado_conservacion;
	}

	public GrupoAnimal getGrupoAnimal() {
		return grupo_animal;
	}

	public EstructuraAnimal getEstructuraAnimal() {
		return estructura_animal;
	}

	public void setIdAnimal(int id_animal) {
		this.id_animal = id_animal;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public void setNombreVulgar(String nombre_vulgar) {
		this.nombre_vulgar = nombre_vulgar;
	}

	public void setOtrasDenominaciones(String otras_denominaciones) {
		this.otras_denom = otras_denominaciones;
	}

	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
	}

	public void setEstadoConservacion(EstadoConservacion estado_conservacion) {
		this.estado_conservacion = estado_conservacion;
	}

	public void setGrupoAnimal(GrupoAnimal grupo_animal) {
		this.grupo_animal = grupo_animal;
	}

	public void setEstructuraAnimal(EstructuraAnimal estructura_animal) {
		this.estructura_animal = estructura_animal;
	}

}