package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "RestriccionPerimetral")
public class RestriccionPerimetral {
	
	@Id
	@Column
	private int idRestriccion;

	@Column
	private int idUsuarioAdministrativo;

	@Column
	private int idUsuarioDamnificada;
	
	@Column
	private int idUsuarioVictimario;
	
	@Column
	private int distancia;

	public RestriccionPerimetral() {
		
	}

	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public int getIdUsuarioAdministrativo() {
		return idUsuarioAdministrativo;
	}

	public void setIdUsuarioAdministrativo(int idUsuarioAdministrativo) {
		this.idUsuarioAdministrativo = idUsuarioAdministrativo;
	}

	public int getIdUsuarioDamnificada() {
		return idUsuarioDamnificada;
	}

	public void setIdUsuarioDamnificada(int idUsuarioDamnificada) {
		this.idUsuarioDamnificada = idUsuarioDamnificada;
	}

	public int getIdUsuarioVictimario() {
		return idUsuarioVictimario;
	}

	public void setIdUsuarioVictimario(int idUsuarioVictimario) {
		this.idUsuarioVictimario = idUsuarioVictimario;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
}
