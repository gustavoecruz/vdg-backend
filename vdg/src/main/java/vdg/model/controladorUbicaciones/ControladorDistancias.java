package vdg.model.controladorUbicaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vdg.controller.RestriccionPerimetralController;
import vdg.model.domain.Infraccion;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.logica.CalculadorDistancias;

public class ControladorDistancias implements Observer {

	private List<RestriccionPerimetral> restricciones;
	private RestriccionPerimetralController restriccionController;

	public ControladorDistancias() {
		restricciones = new ArrayList<RestriccionPerimetral>();
		// TRAIGO RESTRICCIONES DE LA DB
		restricciones = restriccionController.listar();
	}

	@Override
	public void update(Map<Integer, Ubicacion> ubicaciones) {
		calcularDistancias(ubicaciones);
		System.out.println("Se actualizó");
	}

	public void calcularDistancias(Map<Integer, Ubicacion> ubicaciones) {
		// RECORRO LAS RESTRICCIONES PERIMETRALES
		for (RestriccionPerimetral r : restricciones) {
			// OBTENGO LAS UBICACIONES DE LAS PERSONAS DE LA RESTRICCION PERIMETRAL
			Ubicacion ubicacionVictimario = ubicaciones.get(r.getIdVictimario());
			Ubicacion ubicacionDamnificada = ubicaciones.get(r.getIdDamnificada());

			// GENERO LA DISTANCIA ENTRE LAS DOS UBICACIONES Y COMPARO CON LA DISTANCIA DE
			// LA RESTRICCION
			int distancia = generarDistancias(ubicacionVictimario, ubicacionDamnificada).intValue();
			if (distancia <= r.getDistancia()) {
				// GENERO LA INFRACCION
				generarInfraccion(distancia);
			}
		}
	}

	// FALTA BUSCAR UN SERVICIO Y LLAMARLO REEMPLAZANDO LA FORMULA DE HAVERSINE
	public Double generarDistancias(Ubicacion ubicacionVictimario, Ubicacion ubicacionDamnificada) {
		return CalculadorDistancias.obtenerDistancia(ubicacionVictimario.getLatitud(), ubicacionVictimario.getLongitud(), ubicacionDamnificada.getLatitud(), ubicacionDamnificada.getLongitud());
	}

	// FALTA VER SI LA INFRACCION ESTA ACTIVA
	public void generarInfraccion(int distancia) {
		Infraccion nuevaInfraccion = new Infraccion();
		nuevaInfraccion.setDistancia(distancia);
		// GUARDAR EN DB SI NO HAY UNA ACTIVA
	}

	// FALTA LLAMAR A ESTE METODO NOSE CUANDO
	public void actualizarRestricciones() {
		restricciones = restriccionController.listar();
	}

}
