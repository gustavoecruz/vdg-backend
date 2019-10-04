package vdg.model.dto;

import vdg.model.domain.Incidencia;
import vdg.model.domain.Notificacion;

public class NotificacionDTO {
	
	private Notificacion notificacion;
	private Incidencia incidencia;
	
	public NotificacionDTO(Notificacion notificacion, Incidencia incidencia) {
		this.notificacion = notificacion;
		this.incidencia = incidencia;
	}

	public Notificacion getNotificacion() {
		return notificacion;
	}

	public void setNotificacion(Notificacion notificacion) {
		this.notificacion = notificacion;
	}

	public Incidencia getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}
	
	

}
