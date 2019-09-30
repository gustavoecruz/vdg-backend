package vdg.model.controladorUbicaciones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vdg.controller.UbicacionController;
import vdg.model.controladorIncidencias.ControladorIncidencias;

@Component
public class ControladorUbicaciones implements Subject{

	private List<Ubicacion> ubicacionesPersonas;
	private Map<Integer, Ubicacion> mapUbicacionPersona;
	@Autowired
	private UbicacionController ubicacionController = new UbicacionController();
	@Autowired
	private ControladorIncidencias controladorIncidencias= new ControladorIncidencias();
	
	private List<Observer> observers;

/*	public ControladorUbicaciones() {
		this.observers = new ArrayList<Observer>();
		//TRAIGO LAS UBICACIONES QUE ESTAN EN LA DB
		this.ubicacionesPersonas = this.ubicacionController.listar();
	}
	*/
	public void iniciar() {
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
		
		timer.scheduleAtFixedRate(tareaTimer, 0, 15000);
	}

	//FALTA LLAMAR A INCIDENCIAS
	public void controlarPerdidaLocalizacion() {
		//TENGO LA FECHA ACTUAL Y LE RESTO 10 MINUTOS PARA VER CUALES ESTAN ILOCALIZABLES		
		Date ahora = new Date();
		ahora.setTime(ahora.getTime()-600000);
		//CREO EL TIMESTAMP PARA OBTENER LAS QUE SEAN ANTERIORES A ESE TIMESTAMP Y CONTROLO
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		controladorIncidencias.controlarIlocalizables(ahoraStamp);
	}
	
	@Override
	public void notificar(Map<Integer, Ubicacion> ubicaciones) {
		for(int i = 0; i<observers.size(); i++) {
			this.observers.get(i).update(ubicaciones);
		}
	}

	
	public static void main(String[] args) {	
		ControladorDistancias controladorDistancias = new ControladorDistancias();
		ControladorUbicaciones controladorUbicaciones = new ControladorUbicaciones();
		controladorUbicaciones.enlazarObservador(controladorDistancias);
		controladorUbicaciones.actualizarUbicaciones();
		
	}
	
}
