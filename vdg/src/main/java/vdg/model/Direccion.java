package vdg.model;

import javax.persistence.*;

@Entity
@Table(name = "Direccion")
public class Direccion {
	
	@Id
	@Column
	private int idDireccion;

	@Column
	private String calle;

	@Column
	private String altura;
	
	@Column
	private String piso;
	
	@Column
	private String departamento;

	@Column
	private int idLocalidad;
	
	public Direccion() {
		
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	
	
}
