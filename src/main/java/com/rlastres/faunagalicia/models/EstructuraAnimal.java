package com.rlastres.faunagalicia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="estructura")
@Table(name="estructuraanimal")
public class EstructuraAnimal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_estructura;
	private String estructura;
	
	public EstructuraAnimal() {}
	
	public EstructuraAnimal(String estructura) {
		this.estructura = estructura;
	}
	
	public int getIdEstructura() {
		return id_estructura;
	}
	public String getEstructura() {
		return estructura;
	}
	public void setIdEstructura(int id_estructura) {
		this.id_estructura = id_estructura;
	}
	public void setEstructura(String estructura) {
		this.estructura = estructura;
	}

}