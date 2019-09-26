package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.dto.ErrorDTO;
import vdg.model.validadores.ValidadoresRestriccion;
import vdg.repository.RestriccionPerimetralRepository;

@RestController
@RequestMapping("/RestriccionPerimetral")
@CrossOrigin
public class RestriccionPerimetralController {

	@Autowired
	private RestriccionPerimetralRepository restriccionPerimetralRepo;
	
	@Autowired
	ValidadoresRestriccion validador = new ValidadoresRestriccion();
	
	@GetMapping
	public List<RestriccionPerimetral> listar() {
		return restriccionPerimetralRepo.findAll();
	}

	@PostMapping
	public ErrorDTO agregar(@RequestBody RestriccionPerimetral restriccionPerimetral) {
		ErrorDTO error = validador.validarAltaRestriccion(restriccionPerimetral);
		if(error.getHayError()) {
			return error;
		}
		restriccionPerimetralRepo.save(restriccionPerimetral);
		return error;
	}

	@DeleteMapping("/borrar/{id}")
	public void borrar(@PathVariable("id") int id) {
		RestriccionPerimetral r = new RestriccionPerimetral();
		r.setIdRestriccion(id);
		restriccionPerimetralRepo.delete(r);
	}

	@GetMapping("/getByAdministrativo/{id}")
	public List<RestriccionPerimetral> getByAdministrativo(@PathVariable("id") int id) {
		return restriccionPerimetralRepo.findByIdUsuario(id);
	}

	@GetMapping("/getByDamnificada/{id}")
	public List<RestriccionPerimetral> getByDamnificada(@PathVariable("id") int idPersona) {
		return restriccionPerimetralRepo.findByIdDamnificada(idPersona);
	}

	@GetMapping("/getByVictimario/{id}")
	public List<RestriccionPerimetral> getByVictimario(@PathVariable("id") int idPersona) {
		return restriccionPerimetralRepo.findByIdVictimario(idPersona);
	}

	public List<RestriccionPerimetral> getByPersona(int idPersona) {
		List<RestriccionPerimetral> ret = restriccionPerimetralRepo.findByIdVictimario(idPersona);
		if(ret != null)
			return ret;
		ret = restriccionPerimetralRepo.findByIdDamnificada(idPersona);
		return ret;
	}
	
}
