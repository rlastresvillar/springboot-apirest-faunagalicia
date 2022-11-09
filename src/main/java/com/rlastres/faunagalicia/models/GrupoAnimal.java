package com.rlastres.faunagalicia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gruposanimales")
public class GrupoAnimal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_grupo;
	private String grupo;
	
	public GrupoAnimal() {}
	
	public GrupoAnimal(String grupo) {
		this.grupo = grupo;
	}

	public int getIdGrupo() {
		return id_grupo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setIdGrupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

}