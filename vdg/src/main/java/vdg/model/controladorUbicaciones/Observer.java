package vdg.model.controladorUbicaciones;

import java.util.List;

public interface Observer {

	//REALIZAR TAREA CUANDO SE NOTIFICA QUE HAY CAMBIOS
	public void update(List<Ubicacion> ubicaciones);
	
}
