package vdg.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.controladorRutina.ControladorRutina;
import vdg.model.controladorRutina.HistorialUbicacionFecha;
import vdg.model.controladorRutina.UbicacionRutina;
import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.repository.UbicacionRutinaRepository;

@RestController
@RequestMapping("/UbicacionRutina")
@CrossOrigin
public class UbicacionRutinaController {
	
	@Autowired
	private UbicacionRutinaRepository ubicacionRutinaRepo;

	@Autowired
	private ControladorRutina controladorRutina;
	
	@GetMapping
	public List<UbicacionRutina> listar(){
/*PRUEBA CLASE HISTORIALUBICACION
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		ahora.setDate(3);
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		BigDecimal lat = new BigDecimal(-34.586305);
		BigDecimal lon = new BigDecimal(-58.644667);
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setIdPersona(1);
		ubicacion.setFecha(ahoraStamp);
		ubicacion.setLatitud(lat);
		ubicacion.setLongitud(lon);
		ubicacion = historial.dameUbicacionHabitual(ubicacion);
		System.out.println("--------------------");
		System.out.println("Está en rutina? : " + controladorRutina.estaEnRutina(ubicacion));
		System.out.println("--------------------");
*/
		return ubicacionRutinaRepo.findAll();
	}
	
	public List<UbicacionRutina> getUbicacionesPersonaFecha(int idPersona, int dia){
		return ubicacionRutinaRepo.findByPersonaAndDia(idPersona, dia);
	}
	
}
