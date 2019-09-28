package vdg.model.controladorUbicaciones;

import java.util.Map;

public interface Observer {

	//REALIZAR TAREA CUANDO SE NOTIFICA QUE HAY CAMBIOS
	public void update(Map<Integer,Ubicacion> ubicaciones);
	
}
