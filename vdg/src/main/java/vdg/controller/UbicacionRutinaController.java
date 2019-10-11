package vdg.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vdg.model.controladorRutina.ControladorRutina;
import vdg.model.controladorRutina.HistorialUbicacionFecha;
import vdg.model.controladorRutina.UbicacionRutina;
import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.domain.Provincia;
import vdg.repository.UbicacionRutinaRepository;

@RestController
@RequestMapping("/UbicacionRutina")
@CrossOrigin
public class UbicacionRutinaController {
	
	@Autowired
	private UbicacionRutinaRepository ubicacionRutinaRepo;

	@Autowired
	private ControladorRutina controladorRutina;
	
	@Autowired
	private HistorialUbicacionFecha historial;
	
	@GetMapping
	public List<UbicacionRutina> listar(){
/*
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		ahora.setDate(0);
		ahora.setHours(6);
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		BigDecimal lat = new BigDecimal(-34.586305);
		BigDecimal lon = new BigDecimal(-58.644667);
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setIdPersona(1);
		ubicacion.setFecha(ahoraStamp);
		ubicacion.setLatitud(lat);
		ubicacion.setLongitud(lon);		
		System.out.println("--------------------");
		System.out.println("Está en rutina? : " + controladorRutina.estaEnRutina(ubicacion));
		System.out.println("--------------------");
*/
		return ubicacionRutinaRepo.findAll();
	}
	
	@GetMapping("/{ubicacionActual}")
	public Ubicacion ubicacionHabitual(@PathVariable("ubicacionActual") String ubicacionActual)
			throws JsonParseException, JsonMappingException, IOException{
		Ubicacion ubicacion = new ObjectMapper().readValue(ubicacionActual, Ubicacion.class);
		//BUSCO LA UBICACION HABITUAL PARA ESE DIA Y ESA HROA Y LA RETORNO
		Ubicacion nueva = new Ubicacion();
		nueva = historial.dameUbicacionHabitual(ubicacion);
		
		return nueva;
	}

	public List<UbicacionRutina> getUbicacionesPersonaFecha(int idPersona, int dia, int hora){
		
		for(UbicacionRutina ubicacion: ubicacionRutinaRepo.findByPersonaAndDia(idPersona, dia, hora)) {
			System.out.println(ubicacion.getFecha());
		}
		
		return ubicacionRutinaRepo.findByPersonaAndDia(idPersona, dia, hora);
	}
	
	@PostMapping
	public UbicacionRutina agregar(@RequestBody UbicacionRutina ubicacionRutina) {
		//DEJO SOLO LA HORA Y LA FECHA 
		ubicacionRutina.getFecha().setMinutes(0);
		ubicacionRutina.getFecha().setSeconds(0);
		ubicacionRutina.getFecha().setNanos(0);
		
		return ubicacionRutinaRepo.save(ubicacionRutina);
	}
	
}
