package vdg.model.controladorRutina;

import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

public class ControladorRutina {

	//VERIFICO SI LA UBICACION ACTUAL ESTA DENTRO DE UN ÁREA COMUN PARA ESE DIA Y HORARIO
	public boolean estaEnRutina(Ubicacion ubicacionActual) {
		HistorialUbicacionFecha historialPersona = new HistorialUbicacionFecha(ubicacionActual);
		
		Ubicacion ubicacionHabitual = historialPersona.dameUbicacionHabitual(); 
		
		if(ubicacionHabitual!=null)
			if(CalculadorDistancias.obtenerDistancia(ubicacionActual.getLatitud(), ubicacionActual.getLongitud(),
					ubicacionHabitual.getLatitud(), ubicacionHabitual.getLongitud()) >= 100)
				return false;
		
		return true;
	}

}


