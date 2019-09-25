package vdg.model.controladorUbicaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vdg.model.domain.Persona;

public class UbicacionPersona {
	
	private Map<Persona, Coordenada> ubicaciones;
	
	
	public void cargarPersonas(List<Persona> personas) {
		
		ubicaciones = new HashMap<Persona, Coordenada>();
		
		for (Persona persona : personas) {
			ubicaciones.put(persona, null);
		}
	}
	
	//METODO QUE CONSULTA LA COORDENADA Y LA ACTUALIZA
	public void actualizarUbicaciones() {
		
	}
	

}
