package com.rlastres.faunagalicia.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity(name="estructura")
@Table(name="estructuraanimal")
public class EstructuraAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name="id_estructura")
	private int idEstructura;
	
	@Schema(example= "Vertebrados", description = "Nombre de la estructura")
	@NotBlank
	private String estructura;
	
	public EstructuraAnimal() {}
	
	public EstructuraAnimal(String estructura) {
		this.estructura = estructura;
	}
	
	public int getIdEstructura() {
		return idEstructura;
	}
	public String getEstructura() {
		return estructura;
	}
	public void setIdEstructura(int id_estructura) {
		this.idEstructura = id_estructura;
	}
	public void setEstructura(String estructura) {
		this.estructura = estructura;
	}

}