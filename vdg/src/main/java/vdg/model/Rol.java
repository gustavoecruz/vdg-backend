package vdg.model;

import javax.persistence.*;

@Entity
@Table(name= "rol")
public class Rol {
	
	@Id
	@Column
	private int idRol;
	
	@Column
	private String nombre;

	public Rol() {
		
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
