package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "Localidad")
public class Localidad {
	
	@Id
	@Column
	private int idLocalidad;

	@Column
	private String nombre;

	public Localidad() {
		
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
