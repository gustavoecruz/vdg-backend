package vdg.model.controladorRutina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

@Component
public class ControladorRutina {

	@Autowired
	HistorialUbicacionFecha historialPersona = new HistorialUbicacionFecha();
		
	//VERIFICO SI LA UBICACION ACTUAL ESTA DENTRO DE UN ÁREA COMUN PARA ESE DIA Y HORARIO
	public boolean estaEnRutina(Ubicacion ubicacionActual) {
		
		Ubicacion ubicacionHabitual = historialPersona.dameUbicacionHabitual(ubicacionActual); 
		
		System.out.println(ubicacionHabitual.getLatitud() + "/" + ubicacionHabitual.getLongitud());
		
		Double distancia = null;
		
		if(ubicacionHabitual!=null) {
			distancia = CalculadorDistancias.obtenerDistancia(ubicacionActual.getLatitud(), ubicacionActual.getLongitud(),
					ubicacionHabitual.getLatitud(), ubicacionHabitual.getLongitud());
			if(distancia <= 200)
				return true;
		}
		
		return false;
	}

}


