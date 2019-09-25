package vdg.model.controladorUbicaciones;

import java.util.Timer;
import java.util.TimerTask;

public class ControladorUbicacionPersona {
	
	private UbicacionPersona listaPersonas;
	
	//RECORRER CADA CIERTO TIEMPO Y ACTUALIZAR LA UBICACION DE LAS PERSONAS
	public void consultarUbicaciones() {
		
		Timer timer = new Timer();
		
		TimerTask tarea = new TimerTask() {
			
			@Override
			public void run() {
				listaPersonas.actualizarUbicaciones();
				//ENVIAR LISTA AL CONTROLADOR DE INFRACCIONES
			}
		};
		
		timer.schedule(tarea, 0, 5000);
		
	}

}
