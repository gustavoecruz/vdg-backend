package vdg.model.controladorUbicaciones;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import vdg.controller.IncidenciaController;
import vdg.controller.InfraccionController;
import vdg.model.domain.EstadoInfraccion;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Infraccion;
import vdg.model.domain.TipoIncidencia;

public class ControladorInfracciones {
	
	@Autowired
	private InfraccionController infraccionController;

	@Autowired
	private IncidenciaController incidenciaController;

	public ControladorInfracciones() {
		infraccionController = new InfraccionController();
		incidenciaController = new IncidenciaController();
	}
	
	//FALTA VER LA REPETICION DE LA INFRACCION.
	//SI ESTA ACTIVA MODIFICAR LA INFRACCION E INCIDENCIA. SINO CREAR INFRACCION E INCIDENCIA
	public void controlarInfraccionActiva(int distancia, int idRestriccion){
		boolean activa = false;
		//
		if(activa)
			System.out.println("modificar infraccion e incidencia");
		else
			generarInfraccionIncidencia(distancia, idRestriccion);
		
	}
	
	public void generarInfraccionIncidencia(int distancia, int idRestriccion){
		//GENERO LA NUEVA INFRACCION Y SETEO LOS DATOS NECESARIOS
		Infraccion nuevaInfraccion = new Infraccion();
		//OBTENGO LA FECHA ACTUAL PARA LA INFRACCION		
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		//CREO EL TIMESTAMP
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());

		nuevaInfraccion.setFecha(ahoraStamp);
		nuevaInfraccion.setDistancia(distancia);
		nuevaInfraccion.setEstadoInfraccion(EstadoInfraccion.Abierta);
		nuevaInfraccion.setIdRestriccion(idRestriccion);
		infraccionController.agregar(nuevaInfraccion);
		
		//GENERO INCIDENCIA Y SETEO FECHA, DESCRIPCION, TIPICO Y RESTRICCION
		Incidencia nuevaIncidencia = new Incidencia();
		nuevaIncidencia.setFecha(ahoraStamp);
		nuevaIncidencia.setDescripcion("Se cometió una infracción de: " + distancia + " metros");
		nuevaIncidencia.setTopico(TipoIncidencia.InfraccionDeRestriccion);
		nuevaIncidencia.setIdRestriccion(idRestriccion);
		incidenciaController.agregar(nuevaIncidencia);
		
		System.out.println("Infraccion e incidencia creadas");
	}
	
}
