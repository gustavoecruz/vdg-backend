package vdg.model;

import javax.persistence.*;

@Entity
@Table(name = "Localidad")
public class Localidad {
	
	@Id
	@Column
	private int idLocalidad;

	@Column
	private String nombre;

	@Column
	private String codigoPostal;

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

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
}
