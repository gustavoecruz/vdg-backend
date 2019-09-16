package vdg.controller.dto;

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

import vdg.controller.DireccionController;
import vdg.controller.PersonaController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Direccion;
import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;
import vdg.model.validadores.ValidadoresFormPersona;

@RestController
@RequestMapping("/FormABMPersona")
@CrossOrigin
public class FormPersonaController {
	@Autowired
	PersonaController personaController;
	@Autowired
	UsuarioController usuarioController;
	@Autowired
	DireccionController direccionController;
	@Autowired
	ValidadoresFormPersona validador = new ValidadoresFormPersona();

	@GetMapping
	public List<Persona> listar() {
		return personaController.listar();
	}

	@PostMapping
	public ErrorDTO agregar(@RequestBody vdg.model.dto.FormPersonaDTO personaDTO) {
		
		Persona persona = personaDTO.getPersona();
		Usuario usuario = personaDTO.getUsuario();
		Direccion direccion = personaDTO.getDireccion();

		//Valido el usuario y la persona. La dirección se valida en el front
		ErrorDTO error = validador.validarAgregarPersona(persona, usuario);
		if(error.getHayError()) {
			return error;
		}
		//Si los datos son válidos, paso a crear el usuario, direccion y persona, luego FOTO.
		
		usuario.setContrasena(persona.getDNI());
		//usuario.setContrasena(GeneradorContraseña.generarContraseña());
		usuarioController.agregar(usuario);
		int idUsuarioCreado = usuarioController.findByEmail(usuario.getEmail()).getIdUsuario();

		// BUSCAR ID DE LOCALIDAD Y ASIGNARLO A LA DIRECCIÓN
		direccionController.agregar(direccion);
		int idDireccionCreada = direccionController.getId(direccion);

		persona.setIdUsuario(idUsuarioCreado);
		persona.setIdDireccion(idDireccionCreada);
		personaController.agregar(persona);
		
		// AGREGAR FOTO DE PERFIL
		
		return error;
	}

	@DeleteMapping("/{id}")
	public ErrorDTO borrar(@PathVariable("id") int id) {
		Persona p = personaController.getById(id);
		int idUsuario = p.getIdUsuario();
		int idDireccion = p.getIdDireccion();
		ErrorDTO ret = validador.validarBorrarPersona(p);
		if(ret.getHayError()) {
			return ret;
		}
		
		personaController.borrar(id);
		usuarioController.borrar(idUsuario);
		direccionController.borrar(idDireccion);
		// Borrar Foto de perfil
		return ret;
	}

}
