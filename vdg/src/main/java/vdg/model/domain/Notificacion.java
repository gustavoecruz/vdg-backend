package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "Notificacion")
public class Notificacion {
	
	@Id
	@Column
	private int idNotificacion;
	
	@Column
	private int idIncidencia;
	
	@Column
	private int idUsuario;

	@Column
	private boolean visto;

	public Notificacion() {
		// TODO Auto-generated constructor stub
	}

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public int getIdIncidencia() {
		return idIncidencia;
	}

	public void setIdIncidencia(int idIncidencia) {
		this.idIncidencia = idIncidencia;
	}

	public boolean isVisto() {
		return visto;
	}

	public void setVisto(boolean visto) {
		this.visto = visto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
		
}
