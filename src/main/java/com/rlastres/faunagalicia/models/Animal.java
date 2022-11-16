package com.rlastres.faunagalicia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name="animales")
public class Animal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_animal")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private int idAnimal;
	
	@Schema(example= "Lissotriton helveticus", description = "Nombre de la especie")
	@NotBlank
	private String especie;

	@Schema(example= "Sabanduxa palmada", description = "Nombre común del animal")
	@NotBlank
	@Column(name="nombre_vulgar")
	private String nombreVulgar;
	
	@Schema(example= "Tritón palmado", description = "Otros nombres comunes del animal")
	@Column(name="otras_denom")
	private String otrasDenominaciones;
	
	@Schema(example= "Autóctona", description = "Distribución del animal en Galicia")
	private String distribucion;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = EstadoConservacion.class)
    @JoinColumn(name = "id_estado")
	private EstadoConservacion estado_conservacion;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = GrupoAnimal.class)
    @JoinColumn(name = "id_grupo")
	private GrupoAnimal grupo_animal;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = EstructuraAnimal.class)
    @JoinColumn(name = "id_estructura")
	private EstructuraAnimal estructura_animal;
	
	public Animal() {}

	public int getIdAnimal() {
		return idAnimal;
	}

	public String getEspecie() {
		return especie;
	}

	public String getNombreVulgar() {
		return nombreVulgar;
	}

	public String getOtrasDenominaciones() {
		return otrasDenominaciones;
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
		this.idAnimal = id_animal;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public void setNombreVulgar(String nombre_vulgar) {
		this.nombreVulgar = nombre_vulgar;
	}

	public void setOtrasDenominaciones(String otras_denominaciones) {
		this.otrasDenominaciones = otras_denominaciones;
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