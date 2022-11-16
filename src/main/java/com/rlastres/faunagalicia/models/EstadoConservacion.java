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
@Table(name="estadosconservacion")
public class EstadoConservacion {

	@Schema(example= "2", description = "ID del estado de conservación")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@Column(name="id_estado")
	private int idEstado;
	
	@Schema(example= "EX", description = "Código del estado de conservación")
	@NotBlank
	private String codigo;
	
	@Schema(example= "Extinta", description = "Descripción del estado de conservación")
	@NotBlank
	private String estado;
	
	public EstadoConservacion() {}
	
	public EstadoConservacion(String codigo, String estado) {
		this.codigo = codigo;
		this.estado = estado;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setIdEstado(int id_estado) {
		this.idEstado = id_estado;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}