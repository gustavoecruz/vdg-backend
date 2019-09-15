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
import vdg.model.domain.Persona;
import vdg.repository.PersonaRepository;

@RestController
@RequestMapping("/Persona")
@CrossOrigin
public class PersonaController {

	@Autowired
	private PersonaRepository personaRepo;

	@GetMapping
	public List<Persona> listar() {
		return personaRepo.findAll();
	}

	@PostMapping
	public Persona agregar(@RequestBody Persona persona) {
		System.out.println(persona.getIdDireccion()+" ES EL ID DIREC\n"+persona.getIdUsuario()+" ES ID DE USER");
		return personaRepo.save(persona);
	}

	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Persona p = new Persona();
		p.setIdPersona(id);
		personaRepo.delete(p);
	}

	@GetMapping("/GetById/{id}")
	public Persona getById(@PathVariable("id") int id) {
		List<Persona> personas = personaRepo.findById(id);
		return personas.isEmpty() ? null : personas.get(0);
	}

	@GetMapping("/GetByDni/{dni}")
	public Persona getByDni(@PathVariable("dni") String dni) {
		List<Persona> personas = personaRepo.findByDni(dni);
		return personas.isEmpty() ? null : personas.get(0);
	}

}
