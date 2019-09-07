package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.Persona;
import vdg.repository.PersonaRepository;

@RestController
@RequestMapping("/Persona")
public class PersonaController {
	
	@Autowired
	private PersonaRepository personaRepo;
		
	@GetMapping
	public List<Persona> listar(){
		return personaRepo.findAll();
	}
	
	@PostMapping
	public Persona agregar(@RequestBody Persona persona){
		return personaRepo.save(persona);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Persona p = new Persona();
		p.setIdPersona(id);
		personaRepo.delete(p);
	}


}
