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
import vdg.model.domain.Notificacion;
import vdg.repository.IncidenciaRepository;
import vdg.repository.NotificacionRepository;
import vdg.repository.RestriccionPerimetralRepository;

@RestController
@RequestMapping("/Incidencia")
@CrossOrigin
public class IncidenciaController {

	@Autowired
	private IncidenciaRepository incidenciaRepo;

	@Autowired
	private NotificacionRepository notificacionRepo;

	@Autowired
	private RestriccionPerimetralController restriccionController;

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
	
	public Incidencia getById(int idIncidencia) {
		return incidenciaRepo.findByIdIncidencia(idIncidencia);
	}

	@PostMapping
	public Incidencia agregar(@RequestBody Incidencia incidencia) {		
		//GUARDO LA INCIDENCIA
		Incidencia nuevaIncidencia = incidenciaRepo.save(incidencia);
		//CREO LA NOTIFICAICON CON LOS DATOS DE LA INCIDENCIA CREADA Y LA GUARDO
		/*
		Notificacion notificacion = new Notificacion();
		notificacion.setIdIncidencia(incidenciaRepo.getUltimaIncidencia(nuevaIncidencia.getIdRestriccion()).get(0).getIdIncidencia());
		notificacion.setIdUsuario(restriccionController.getByIdRestriccion(nuevaIncidencia.getIdRestriccion()).getIdUsuario());
		notificacion.setVisto(false);
		notificacionRepo.save(notificacion);
		*/
		return nuevaIncidencia;
	}
}
