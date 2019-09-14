package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vdg.controller.PersonaController;
import vdg.controller.RestriccionPerimetralController;
import vdg.model.domain.Persona;

@Component
public class ValidadoresPersona {

	@Autowired
	private PersonaController personaController;
	@Autowired
	private RestriccionPerimetralController restriccionController;

	public boolean validarAltaPersona(Persona persona) {
		return existePersona(persona);
	}

	public boolean validarBajaPersona(Persona persona) {
		return participaEnRestriccion(persona);
	}

	private boolean participaEnRestriccion(Persona persona) {
		return restriccionController.getByPersona(persona.getIdPersona()) != null;
	}

	private boolean existePersona(Persona persona) {
		return personaController.getByDni(persona.getDNI()) != null;
	}

}
