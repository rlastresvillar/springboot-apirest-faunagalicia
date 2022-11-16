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

@Entity
@Table(name="gruposanimales")
public class GrupoAnimal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name="id_grupo")
	private int idGrupo;
	
	@Schema(example= "Anfibios", description = "Nombre del grupo animal")
	@NotBlank
	private String grupo;
	
	public GrupoAnimal() {}
	
	public GrupoAnimal(String grupo) {
		this.grupo = grupo;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setIdGrupo(int id_grupo) {
		this.idGrupo = id_grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

}