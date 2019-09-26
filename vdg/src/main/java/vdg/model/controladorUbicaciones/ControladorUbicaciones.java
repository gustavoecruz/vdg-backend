package vdg.model.controladorUbicaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import vdg.controller.UbicacionController;


public class ControladorUbicaciones implements Subject{

	private List<Ubicacion> ubicacionesPersonas;
	//CONTROLADOR DE UBICACIONES PARA CONSULTAR LA DB
	private UbicacionController ubicacionController;

	private List<Observer> observers;

	public ControladorUbicaciones() {
		this.observers = new ArrayList<Observer>();
		//TRAER LAS UBICACIONES QUE ESTAN EN LA DB Y GUARDARLAS EN LA LISTA ubicacionesPersonas
//		this.ubicacionesPersonas = this.ubicacionController.listar();
	}
	
	public void enlazarObservador(Observer o) {
		this.observers.add(o);
	}

	public void actualizarUbicaciones() {
		
		Timer timer = new Timer();
		
		TimerTask tareaTimer = new TimerTask() {
			
			@Override
			public void run() {
				//RECORRER LAS ubicacionesPersonas PARA ACTUALIZARLAS
					
					//CONSULTAR LA UBICACION NUEVA Y GUARDAR EN LA BD
					
					//SI ALGUNA UBICACION DEVUELVE UN NULL CHEQUEAR LA SITUACION "controlarPerdidaLocalización(ubicacion)"
				
				//NOTIFICAR QUE LAS UBICACIONES SE ACTUALIZARON A LOS OBSERVERS
				notificar(ubicacionesPersonas);
			}
		};
		
		timer.scheduleAtFixedRate(tareaTimer, 0, 2000);
	}

	public void controlarPerdidaLocalización(Ubicacion ubicacion) {
		//VER DIFERENCIA DE TIMESTAMP, SI PASA DE X TIEMPO SE GENERA UN INCIDENCIA Y SE NOTIFICA A ADM
	}
	
	@Override
	public void notificar(List<Ubicacion> ubicaciones) {
		for(int i = 0; i<observers.size(); i++) {
			this.observers.get(i).update(ubicaciones);
		}
	}

	/*
	public static void main(String[] args) {
		
		ControladorDistancias controladorDistancias = new ControladorDistancias();
		ControladorUbicaciones controladorUbicaciones = new ControladorUbicaciones();
		controladorUbicaciones.enlazarObservador(controladorDistancias);
		controladorUbicaciones.actualizarUbicaciones();
		
	}
	*/
}
