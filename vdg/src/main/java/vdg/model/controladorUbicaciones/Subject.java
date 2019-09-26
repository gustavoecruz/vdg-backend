package vdg.model.controladorUbicaciones;

import java.util.List;

public interface Subject {
	
	//NOTIFICAR CUANDO ACTULICE UBICACION
	public void notificar(List<Ubicacion> ubicaciones);

}
