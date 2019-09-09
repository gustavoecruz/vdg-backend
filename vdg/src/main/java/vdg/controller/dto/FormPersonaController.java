package vdg.controller.dto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.controller.DireccionController;
import vdg.controller.PersonaController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Direccion;
import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;

@RestController
@RequestMapping("/FormABMPersona")
public class FormPersonaController {
	@Autowired
	PersonaController personaController = new PersonaController();

	@Autowired
	UsuarioController usuarioController = new UsuarioController();
	
	@Autowired
	DireccionController direccionController = new DireccionController();

	@GetMapping
	public List<Persona> listar() {
		return personaController.listar();
	}

	@PostMapping
	public Persona agregar(@RequestBody vdg.model.dto.FormPersonaDTO personaDTO) {

		Persona persona = personaDTO.getPersona();
		Usuario usuario = personaDTO.getUsuario();
		Direccion direccion = personaDTO.getDireccion();
		
		usuario.setContrasena(persona.getDNI());
		usuarioController.agregar(usuario);
		int idUsuarioCreado = usuarioController.findByEmail(usuario.getEmail()).getIdUsuario();
		
		//BUSCAR ID DE LOCALIDAD Y ASIGNARLO A LA DIRECCIÓN
		direccionController.agregar(direccion);
		int idDireccionCreada = direccionController.getId(direccion);
		
		persona.setIdUsuario(idUsuarioCreado);
		persona.setIdDireccion(idDireccionCreada);
		
		personaController.agregar(persona);
		return personaDTO.getPersona();
	}

	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Persona p = personaController.getById(id);
		int idUsuario = p.getIdUsuario();
		int idDireccion = p.getIdDireccion();
		personaController.borrar(id);
		usuarioController.borrar(idUsuario);
		direccionController.borrar(idDireccion);
		//Borrar Foto de perfil
		
	}

}
