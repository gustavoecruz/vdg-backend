package vdg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.Incidencia;
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

}
