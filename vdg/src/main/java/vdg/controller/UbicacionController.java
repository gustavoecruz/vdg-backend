package vdg.controller;

import java.sql.Timestamp;
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

import vdg.model.controladorUbicaciones.Ubicacion;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.dto.UbicacionDTO;
import vdg.repository.UbicacionRepository;

@RestController
@RequestMapping("/Ubicacion")
@CrossOrigin
public class UbicacionController {
	
	@Autowired
	private UbicacionRepository ubicacionRepo;
	@Autowired
	private RestriccionPerimetralController restriccionController;
	
	@GetMapping
	public List<Ubicacion> listar() {
		return ubicacionRepo.findAll();
	}
	
	@GetMapping("/getByRestriccion/{idRestriccion}")
	public UbicacionDTO findByRestriccion(@PathVariable("idRestriccion") int idRestriccion) {
		UbicacionDTO ubiDTO = new UbicacionDTO();
		RestriccionPerimetral restriccion = restriccionController.getByIdRestriccion(idRestriccion);
		ubiDTO.setUbicacionDamnificada(ubicacionRepo.findByIdPersona(restriccion.getIdDamnificada()));
		ubiDTO.setUbicacionVictimario(ubicacionRepo.findByIdPersona(restriccion.getIdVictimario()));
		return ubiDTO;
	}
	
	@PostMapping
	public Ubicacion agregar(@RequestBody Ubicacion ubicacion) {
		return ubicacionRepo.save(ubicacion);
	}
	
	@PutMapping("/{idUbicacion}")
	public Ubicacion modificar(@RequestBody Ubicacion ubicacion, @PathVariable("idUbicacion") int idUbicacion){
		ubicacion.setIdUbicacion(idUbicacion);
		return ubicacionRepo.save(ubicacion);
	}
	
	public List<Ubicacion> getPerdidasDeLocalizacion(Timestamp fechaLimite){
		return ubicacionRepo.findIlocalizables(fechaLimite);
	}

}
