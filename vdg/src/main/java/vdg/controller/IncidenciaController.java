package vdg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Persona;
import vdg.repository.IncidenciaRepository;

@RestController
@RequestMapping("/Incidencia")
@CrossOrigin
public class IncidenciaController {

	@Autowired
	private IncidenciaRepository incidenciaRepo;

	@GetMapping("/{idRestriccion}")
	public List<Incidencia> listar(@PathVariable("idRestriccion") int idRestriccion) {
		return incidenciaRepo.findByIdRestriccion(idRestriccion);
	}
	
	public List<Incidencia> getIncidenciasIlocalizable(int idRestriccion){
		return incidenciaRepo.getIlocalizable(idRestriccion);
	}
	
	public List<Incidencia> getIncidenciasDamnificadaIlocalizable(int idRestriccion){
		return incidenciaRepo.getDamnificadaIlocalizable(idRestriccion);
	}
	
	public List<Incidencia> getIncidenciasVictimarioIlocalizable(int idRestriccion){
		return incidenciaRepo.getVictimarioIlocalizable(idRestriccion);
	}

	@PostMapping
	public Incidencia agregar(@RequestBody Incidencia incidencia) {
		return incidenciaRepo.save(incidencia);
	}
}
