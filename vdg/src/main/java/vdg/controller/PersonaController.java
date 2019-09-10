package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Persona> listar(){
		return personaRepo.findAll();
	}
	
	@PostMapping
	public Persona agregar(Persona persona){
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
		return personaRepo.findById(id);
	}


}
