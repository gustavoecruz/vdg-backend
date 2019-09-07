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
import vdg.model.Usuario;
import vdg.repository.PersonaRepository;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private PersonaRepository personaRepo;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@GetMapping
	public List<Persona> listarPersonas(){
		return personaRepo.findAll();
	}
	
	@PostMapping
	public Persona agregar(@RequestBody Persona persona){
		
		//CREO USUARIO NUEVO CON LOS DATOS DE LA PERSONA
		Usuario usuario = new Usuario();
		usuario.setEmail(persona.getEmail());
		usuario.setContrasena(persona.getDni());
		usuario.setIdRol(1);
		usuarioRepo.save(usuario);
		
		//CONSULTO EL ID DEL USUARIO CREADO CON EL EMAIL Y SE LO PONGO A LA PERSONA
		//persona.setIdUsuario(usuarioRepo.findByEmail(persona.getEmail()).get(0).getIdUsuario());
		
		//CREO LA PERSONA
		return personaRepo.save(persona);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Persona u = new Persona();
		u.setIdPersona(id);
		personaRepo.delete(u);
	}

}
