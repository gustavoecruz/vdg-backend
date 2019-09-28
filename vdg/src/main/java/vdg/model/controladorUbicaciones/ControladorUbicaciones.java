package vdg.model.controladorUbicaciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import vdg.controller.UbicacionController;


public class ControladorUbicaciones implements Subject{

	private List<Ubicacion> ubicacionesPersonas;
	private Map<Integer, Ubicacion> mapUbicacionPersona;
	private UbicacionController ubicacionController;

	private List<Observer> observers;

	public ControladorUbicaciones() {
		this.observers = new ArrayList<Observer>();
		//TRAIGO LAS UBICACIONES QUE ESTAN EN LA DB
		this.ubicacionesPersonas = this.ubicacionController.listar();
	}
	
	public void enlazarObservador(Observer o) {
		this.observers.add(o);
	}

	public void actualizarUbicaciones() {
		
		Timer timer = new Timer();
		
		TimerTask tareaTimer = new TimerTask() {
			
			@Override
			public void run() {
				//ACTUALIZAR LAS ubicacionesPersonas
				ubicacionesPersonas = ubicacionController.listar();					
				//Convertir de LIST a MAP las ubicaciones
				mapUbicacionPersona= ubicacionesPersonas.stream().collect(Collectors.toMap(x->x.getIdPersona(),x->x));			
				//NOTIFICAR QUE LAS UBICACIONES SE ACTUALIZARON A LOS OBSERVERS
				notificar(mapUbicacionPersona);
				//CONTROLAR PERDIDA DE LOCALIZACIÓN PARA VERIFICAR SI SE GENERA INCIDENCIAS
				controlarPerdidaLocalizacion();
			}
		};
		
		timer.scheduleAtFixedRate(tareaTimer, 0, 2000);
	}

	//FALTA LLAMAR A INCIDENCIAS
	public void controlarPerdidaLocalizacion() {
		//TENGO LA FECHA ACTUAL Y LE RESTO 10 MINUTOS PARA VER CUALES ESTAN ILOCALIZABLES		
		Date ahora = new Date();
		ahora.setTime(ahora.getTime()-600000);
		//CREO EL TIMESTAMP PARA OBTENER LAS QUE SEAN ANTERIORES A ESE TIMESTAMP
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		//DE ACA PARA ABAJO IRIA EN LA CLASE DE INCIDENCIAS. LE PASO EL ahoraStamp.
		//OBTENER LAS UBICACIONES DE LAS PERSONAS QUE SE PERDIÓ LA LOCALIZACIÓN
		List<Ubicacion> localizacionesPerdidas = ubicacionController.getPerdidasDeLocalizacion(ahoraStamp);
		for(Ubicacion ubicacion : localizacionesPerdidas) {
			//Llamar a INCIDENCIAS y que AHI se controle si la genero automatica o NO
		}
	}
	
	@Override
	public void notificar(Map<Integer, Ubicacion> ubicaciones) {
		for(int i = 0; i<observers.size(); i++) {
			this.observers.get(i).update(ubicaciones);
		}
	}

	
	public static void main(String[] args) {
		//CREO UBICACIONES DE PRUEBA. UNA VEZ QUE LAS CREASTE Y LA TENES EN LA BD, COMENTA ESTAS CREACIONES;
		
		
		ControladorDistancias controladorDistancias = new ControladorDistancias();
		ControladorUbicaciones controladorUbicaciones = new ControladorUbicaciones();
		controladorUbicaciones.enlazarObservador(controladorDistancias);
		controladorUbicaciones.actualizarUbicaciones();
		
	}
	
}
