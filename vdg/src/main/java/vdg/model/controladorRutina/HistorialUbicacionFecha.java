package vdg.model.controladorRutina;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.UbicacionRutinaController;
import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

public class HistorialUbicacionFecha {
	
	public List<UbicacionRutina> ubicacionesFecha = new ArrayList<UbicacionRutina>();
	
	@Autowired
	private UbicacionRutinaController ubicacionController = new UbicacionRutinaController();
	
	public HistorialUbicacionFecha(Ubicacion ubicacion) {
		//TRAIGO LAS UBICACIONES DE LA PERSONA Y DEL DIA EN PARTICULAR
		this.ubicacionesFecha = this.ubicacionController.getUbicacionesPersonaFecha(ubicacion.getIdPersona(),
				ubicacion.getFecha().getDay());
		//FALTA FILTRAR POR LA HORA
	}

	//DEVUELVE UNA UBICACION PROMEDIO SI LA HAY
	public Ubicacion dameUbicacionHabitual() {

		List<UbicacionRutina> ubicacionesMasRepetidas = new ArrayList<UbicacionRutina>();
		int maxima = 0;
		//RECORRO LAS UBICACIONES PARA QUEDARME CON LA QUE MAS UBICACIONES CERCANAS TENGA (LA QUE MAS SE REPITE)
		for(UbicacionRutina ubicacion: ubicacionesFecha) {
			List<UbicacionRutina> ubicacionesCercanas = getUbicacionesCercanas(ubicacion);
			if(ubicacionesCercanas.size() >= maxima) {
				ubicacionesMasRepetidas = ubicacionesCercanas;
				maxima = ubicacionesCercanas.size();
			}
		}
		
		//CHEQUEO SI LA CANTIDAD DE UBICACIONES QUE QUEDARON ES MAYOR AL 70% Y CALCULO LA UBICACION PROMEDIO PARA RETORNAR
		if((maxima*100)/ubicacionesMasRepetidas.size()>=70) {
			return getUbicacionPromedio(ubicacionesMasRepetidas);
		}
		
		return null;
	}

	//DEVUELVE LA LISTA DE LAS UBICACIONES MAS CERCNAS (AREA DE 100 MTS) A LA UBICACION PASADA
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
	
	//CALCULA LA UBICACION PROMEDIO ENTRE TODAS LAS UBICACIONES CERCANAS (AREA DE 100 MTS)
	public Ubicacion getUbicacionPromedio(List<UbicacionRutina> ubicaciones) {
		
		Ubicacion ubicacionPromedio = new Ubicacion();
		BigDecimal lat = new BigDecimal(0);
		BigDecimal lon = new BigDecimal(0);
		
		for(UbicacionRutina ubicacion: ubicaciones) {
			lat = lat.add(ubicacion.getLatitud());
			lon = lon.add(ubicacion.getLongitud());
		}
		
		ubicacionPromedio.setLatitud(lat.divide(new BigDecimal(ubicaciones.size()), 6, BigDecimal.ROUND_HALF_UP));
		ubicacionPromedio.setLatitud(lon.divide(new BigDecimal(ubicaciones.size()), 6, BigDecimal.ROUND_HALF_UP));
				
		return ubicacionPromedio;
	}
}
