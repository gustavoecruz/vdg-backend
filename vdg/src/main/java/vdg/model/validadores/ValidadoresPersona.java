package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import vdg.controller.PersonaController;
import vdg.model.domain.Persona;

public class ValidadoresPersona {

	@Autowired
	private static PersonaController personaController = new PersonaController();

	public static boolean validarAltaPersona(Persona persona) {
		return existePersona(persona);
	}

	public static boolean validarBajaPersona() {
		return true;
	}

	

	private static boolean existePersona(Persona persona) {
		return personaController.getByDni(persona.getDNI()) != null;
	}

}
