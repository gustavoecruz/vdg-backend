package vdg.model.controladorRutina;

import java.math.BigDecimal;

import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.logica.CalculadorDistancias;

public class ControladorRutina {
	
	

	public static void main(String[] args) {
		
		Ubicacion ubicacion = new Ubicacion();
		UbicacionRutina ubicacionRutina1 = new UbicacionRutina();
		BigDecimal lat1 = new BigDecimal(-34.585733);
		ubicacionRutina1.setLatitud(lat1);
		BigDecimal long1 = new BigDecimal(-58.650988);
		ubicacionRutina1.setLongitud(long1);
		UbicacionRutina ubicacionRutina2 = new UbicacionRutina();
		BigDecimal lat2 = new BigDecimal(-34.586305);
		ubicacionRutina2.setLatitud(lat2);
		BigDecimal long2 = new BigDecimal(-58.644667);
		ubicacionRutina2.setLongitud(long2);
		
		HistorialUbicacionFecha hstorial = new HistorialUbicacionFecha(ubicacion);
		hstorial.ubicacionesFecha.add(ubicacionRutina1);
		hstorial.ubicacionesFecha.add(ubicacionRutina2);
		
		for(UbicacionRutina u: hstorial.ubicacionesFecha) {
			System.out.println(u.getLatitud() +"/"+u.getLongitud());
		}
		
		System.out.println(CalculadorDistancias.obtenerDistancia(lat1, long1, lat2, long2));

	}

}


