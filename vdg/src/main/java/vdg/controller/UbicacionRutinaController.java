package vdg.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	public List<UbicacionRutina> listar() {
		/*
		 * Date ahora = new Date(); ahora.setTime(ahora.getTime()); //6 CAE DOMINGO
		 * ahora.setDate(6); ahora.setHours(6); Timestamp ahoraStamp = new
		 * Timestamp(ahora.getTime()); BigDecimal lat = new BigDecimal(-34.586305);
		 * BigDecimal lon = new BigDecimal(-58.644667); Ubicacion ubicacion = new
		 * Ubicacion(); ubicacion.setIdPersona(1); ubicacion.setFecha(ahoraStamp);
		 * ubicacion.setLatitud(lat); ubicacion.setLongitud(lon);
		 * System.out.println("--------------------");
		 * System.out.println("Está en rutina? : " +
		 * controladorRutina.estaEnRutina(ubicacion));
		 * System.out.println("--------------------");
		 */
		return ubicacionRutinaRepo.findAll();
	}

	@GetMapping("/persona={idPersona}/dia={dia}/hora={hora}/minutos={minutos}")
	public Ubicacion ubicacionRutinaria2(@PathVariable("idPersona") int idPersona, @PathVariable("dia") int dia,
			@PathVariable("hora") int hora, @PathVariable("minutos") int minutos) {
		return historial.dameUbicacionHabitual(idPersona, dia, hora, minutos);
	}

	public List<UbicacionRutina> getUbicacionesPersonaFecha(int idPersona, int dia, int hora, int minutos) {

		for (UbicacionRutina ubicacion : ubicacionRutinaRepo.findByPersonaAndFecha(idPersona, dia, hora, minutos)) {
			System.out.println(ubicacion.getFecha());
		}

		return ubicacionRutinaRepo.findByPersonaAndFecha(idPersona, dia, hora, minutos);
	}

	@PostMapping
	public UbicacionRutina agregar(@RequestBody UbicacionRutina ubicacionRutina) {
		// DEJO SOLO LA HORA Y LA FECHA
		ubicacionRutina.getFecha().setMinutes(0);
		ubicacionRutina.getFecha().setSeconds(0);
		ubicacionRutina.getFecha().setNanos(0);

		return ubicacionRutinaRepo.save(ubicacionRutina);
	}

}
