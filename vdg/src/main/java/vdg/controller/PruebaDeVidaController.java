package vdg.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.EstadoPruebaDeVida;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Persona;
import vdg.model.domain.PruebaDeVida;
import vdg.model.domain.TipoIncidencia;
import vdg.repository.PruebaDeVidaRepository;

@RestController
@RequestMapping("/PruebaDeVida")
@CrossOrigin
public class PruebaDeVidaController {

	@Autowired
	private PruebaDeVidaRepository pruebaDeVidaRepo;
	
	@Autowired
	private IncidenciaController incidenciaController;
	
	@Autowired
	private PersonaController personaController;
	
	@GetMapping("/{idPersona}")
	public List<PruebaDeVida> getPruebasDeVidaPersona(@PathVariable("idPersona") int idPersona){
		return pruebaDeVidaRepo.findByIdPersonaRestriccionOrderByFechaDesc(idPersona);
	}
	
	@PostMapping
	public PruebaDeVida agregar(@RequestBody PruebaDeVida pruebaDeVida) {
		// CREO EL TIMESTAMP
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		pruebaDeVida.setFecha(ahoraStamp);
		pruebaDeVida.setEstado(EstadoPruebaDeVida.Pendiente);
		
		return pruebaDeVidaRepo.save(pruebaDeVida);
	}

	@PutMapping("/{idPruebaDeVida}")
	public PruebaDeVida modificar(@RequestBody PruebaDeVida pruebaDeVida, @PathVariable("idPruebaDeVida") int idPruebaDeVida) {
		
		if(pruebaDeVida.getEstado().equals(EstadoPruebaDeVida.Rechazada))
			generarIncidenciaPruebaDeVida(pruebaDeVida);
		
		pruebaDeVida.setIdPruebaDeVida(idPruebaDeVida);
		return pruebaDeVidaRepo.save(pruebaDeVida);
	}

	private void generarIncidenciaPruebaDeVida(PruebaDeVida pruebaDeVida) {
		Incidencia incidencia = new Incidencia();
		
		//GENERO LA FECHA DE INCIDENCIA
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		//TRAIGO LA PERSONA DE LA PRUEBA DE VIDA
		Persona persona = personaController.getById(pruebaDeVida.getIdPersonaRestriccion());
		
		incidencia.setTopico(TipoIncidencia.PruebaDeVidaFallida);
		incidencia.setIdRestriccion(pruebaDeVida.getIdRestriccion());
		incidencia.setFecha(ahoraStamp);
		incidencia.setDescripcion("Fallo la prueba de vida de: " + persona.getApellido() + ", " +
		persona.getNombre() + ". Descripción de prueba de vida: " + pruebaDeVida.getDescripcion() +
		". Fecha de petición: " + pruebaDeVida.getFecha());
		
		incidenciaController.agregar(incidencia);
	}

}
