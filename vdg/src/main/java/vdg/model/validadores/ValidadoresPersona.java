package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import vdg.controller.PersonaController;
import vdg.controller.RestriccionPerimetralController;
import vdg.model.domain.Persona;

public class ValidadoresPersona {

	@Autowired
	private static PersonaController personaController = new PersonaController();
	@Autowired
	private static RestriccionPerimetralController restriccionController = new RestriccionPerimetralController();

	public static boolean validarAltaPersona(Persona persona) {
		return existePersona(persona);
	}

	public static boolean validarBajaPersona(Persona persona) {
		return participaEnRestriccion(persona);
	}

	private static boolean participaEnRestriccion(Persona persona) {
		return restriccionController.getByPersona(persona.getIdPersona()) != null;
	}

	private static boolean existePersona(Persona persona) {
		return personaController.getByDni(persona.getDNI()) != null;
	}

}
