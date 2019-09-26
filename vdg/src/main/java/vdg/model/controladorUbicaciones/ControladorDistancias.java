package vdg.model.controladorUbicaciones;

import java.util.ArrayList;
import java.util.List;

import vdg.controller.RestriccionPerimetralController;
import vdg.model.domain.Infraccion;
import vdg.model.domain.RestriccionPerimetral;

public class ControladorDistancias implements Observer{

	private List<RestriccionPerimetral> restricciones;
	private RestriccionPerimetralController restriccionController;
	
	public ControladorDistancias() {
		restricciones = new ArrayList<RestriccionPerimetral>();
		//TRAER RESTRICCIONES DE LA DB Y CARGARLAS
//		restricciones = restriccionController.listar();
	}
	
	@Override
	public void update(List<Ubicacion> ubicaciones) {
		//CALCULAR DISTANCIAS CON LAS UBICACIONES
//		calcularDistancias(ubicaciones);
//		System.out.println("Se actualizó");
	}

	public void calcularDistancias(List<Ubicacion> ubicaciones) {
		//RECORRO LAS RESTRICCIONES PERIMETRALES
		for(int i = 0; i < restricciones.size(); i++) {
			Ubicacion ubicacionVictimario = new Ubicacion();
			Ubicacion ubicacionDamnificada = new Ubicacion();
			//GUARDO LAS UBICACIONES DE LAS PERSONAS DE LA RESTRICCION PERIMETRAL
			for(int j = 0; j<ubicaciones.size(); j++) {
				if(ubicaciones.get(j).getIdPersona() == restricciones.get(i).getIdVictimario())
					ubicacionVictimario = ubicaciones.get(j);
				if(ubicaciones.get(j).getIdPersona() == restricciones.get(i).getIdDamnificada())
					ubicacionDamnificada = ubicaciones.get(j);
			}
			//GENERO LA DISTANCIA ENTRE LAS DOS UBICACIONES Y COMPARO CON LA DISTANCIA DE LA RESTRICCION
			int distancia = generarDistancias(ubicacionVictimario, ubicacionDamnificada);
			if(distancia <= restricciones.get(i).getDistancia()) {
				//GENERO LA INFRACCION
				generarInfraccion(distancia);
			}
		}
	}
	
	public int generarDistancias(Ubicacion ubicacionVictimario, Ubicacion ubicacionDamnificada) {
		//LLAMAR AL SERVICIO QUE CALCULARA LA DISTANCIA ENTRE DOS UBICACIONES Y RETORNAR
		return 0;
	}
	
	public void generarInfraccion(int distancia) {
		Infraccion nuevaInfraccion = new Infraccion();
		nuevaInfraccion.setDistancia(distancia);
		//GUARDAR EN DB
	}
	
}
