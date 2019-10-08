package vdg.model.controladorRutina;

import java.util.ArrayList;
import java.util.List;

import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

public class HistorialUbicacionFecha {
	
	public List<UbicacionRutina> ubicacionesFecha;
	
	public HistorialUbicacionFecha(Ubicacion ubicacion) {
		//Traer las fechas de esa persona y ese día del a db
		this.ubicacionesFecha = new ArrayList<UbicacionRutina>();
	}

	public UbicacionRutina dameUbicacionHabitual() {
		
		List<UbicacionRutina> ubicacionesMasRepetidas = new ArrayList<UbicacionRutina>();
		int maxima = 0;
		
		for(UbicacionRutina ubicacion: ubicacionesFecha) {
			List<UbicacionRutina> ubicacionesCercanas = getUbicacionesCercanas(ubicacion);
			if(ubicacionesCercanas.size() >= maxima) {
				ubicacionesMasRepetidas = ubicacionesCercanas;
				maxima = ubicacionesCercanas.size();
			}
		}
		
		//CHEQUEAR LOS PORCENTAJES Y RETORNAR LA UBICACION PROMEDIO
		
		return ubicacionesMasRepetidas.get(0);
	}

	private List<UbicacionRutina> getUbicacionesCercanas(UbicacionRutina ubicacionMedio) {
		
		List<UbicacionRutina> ubicacionesCercanas = new ArrayList<UbicacionRutina>();
				
		for(UbicacionRutina ubicacion: ubicacionesFecha) {
			if(CalculadorDistancias.obtenerDistancia(ubicacionMedio.getLatitud(), ubicacionMedio.getLongitud(),
					ubicacion.getLatitud(), ubicacion.getLongitud()) <= 100) {
				ubicacionesCercanas.add(ubicacion);
			}
		}
		
		return ubicacionesCercanas;
	}
}
