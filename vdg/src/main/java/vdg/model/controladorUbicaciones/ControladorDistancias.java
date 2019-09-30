package vdg.model.controladorUbicaciones;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.RestriccionPerimetralController;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.logica.CalculadorDistancias;

@Component
public class ControladorDistancias implements Observer {

	@Autowired
	private RestriccionPerimetralController restriccionController;
	
	@Autowired
	private ControladorInfracciones controladorInfracciones = new ControladorInfracciones();
	
	private List<RestriccionPerimetral> restricciones;

/*	public ControladorDistancias() {
		// TRAIGO RESTRICCIONES DE LA DB
		if(this.restriccionController == null)
			System.out.println("retriccionController NULL");
		this.restricciones = this.restriccionController.listar();
	}*/
	
	public void iniciar() {
		this.restricciones = this.restriccionController.listar();
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
				//controlarInfraccion(distancia, r.getIdRestriccion());
			}
		}
	}

	// FALTA BUSCAR UN SERVICIO Y LLAMARLO REEMPLAZANDO LA FORMULA DE HAVERSINE
	public Double generarDistancias(Ubicacion ubicacionVictimario, Ubicacion ubicacionDamnificada) {
		return CalculadorDistancias.obtenerDistancia(ubicacionVictimario.getLatitud(), ubicacionVictimario.getLongitud(), ubicacionDamnificada.getLatitud(), ubicacionDamnificada.getLongitud());
	}

	//CONTROLA SI LA INFRACCION ESTA ACTIVA O NO PARA GENERAR O MODIFICAR
	public void controlarInfraccion(int distancia, int idRestriccion) {
		controladorInfracciones.controlarInfraccionActiva(distancia, idRestriccion);
	}

	// FALTA LLAMAR A ESTE METODO NOSE CUANDO
	public void actualizarRestricciones() {
		restricciones = restriccionController.listar();
	}

}
