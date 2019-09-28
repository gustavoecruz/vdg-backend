package vdg.model.controladorUbicaciones;

import java.util.Map;

public interface Subject {
	
	//NOTIFICAR CUANDO ACTULICE UBICACION
	public void notificar(Map<Integer,Ubicacion> ubicaciones);

}
