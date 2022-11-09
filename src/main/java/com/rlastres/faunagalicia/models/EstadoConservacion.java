package com.rlastres.faunagalicia.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estadosconservacion")
public class EstadoConservacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_estado;
	private String codigo;
	private String estado;
	
	public EstadoConservacion() {}
	
	public EstadoConservacion(String codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}

	public int getIdEstado() {
		return id_estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setIdEstado(int id_estado) {
		this.id_estado = id_estado;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}