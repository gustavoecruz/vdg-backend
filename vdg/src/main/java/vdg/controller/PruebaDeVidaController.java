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
import vdg.model.domain.PruebaDeVida;
import vdg.repository.PruebaDeVidaRepository;

@RestController
@RequestMapping("/PruebaDeVida")
@CrossOrigin
public class PruebaDeVidaController {

	@Autowired
	private PruebaDeVidaRepository pruebaDeVidaRepo;
	
	@GetMapping("/{idPersona}")
	public List<PruebaDeVida> getPruebasDeVidaPersona(@PathVariable("idPersona") int idPersona){
		return pruebaDeVidaRepo.findByIdPersonaRestriccion(idPersona);
	}
	
	@PostMapping
	public PruebaDeVida agregar(@RequestBody PruebaDeVida pruevaDeVida) {
		// CREO EL TIMESTAMP
		Date ahora = new Date();
		ahora.setTime(ahora.getTime());
		Timestamp ahoraStamp = new Timestamp(ahora.getTime());
		pruevaDeVida.setFecha(ahoraStamp);
		pruevaDeVida.setEstado(EstadoPruebaDeVida.Pendiente);
		
		return pruebaDeVidaRepo.save(pruevaDeVida);
	}

	@PutMapping("/{idPruebaDeVida}")
	public PruebaDeVida modificar(@RequestBody PruebaDeVida pruevaDeVida, @PathVariable("idPruebaDeVida") int idPruebaDeVida) {
		pruevaDeVida.setIdPruebaDeVida(idPruebaDeVida);
		return pruebaDeVidaRepo.save(pruevaDeVida);
	}

}
