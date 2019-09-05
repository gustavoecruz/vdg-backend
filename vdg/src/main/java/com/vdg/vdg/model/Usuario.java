package com.vdg.vdg.model;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
	
	@Id
	@Column
	private int id;

	@Column
	private String email;

	@Column
	private String contrasenia;

	@Column
	private int rol;
	
	public Usuario() {
		
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getRol() {
		return rol;
	}
	
	public void setRol(int rol) {
		this.rol = rol;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	
}
