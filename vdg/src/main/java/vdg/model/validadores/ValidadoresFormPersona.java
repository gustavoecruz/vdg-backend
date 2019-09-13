package vdg.model.validadores;

import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;

public class ValidadoresFormPersona {
	
	public static ErrorDTO validarAgregarPersona(Persona persona, Usuario usuario) {
		ErrorDTO ret = new ErrorDTO();
		
		if(!ValidadoresPersona.validarAltaPersona(persona)) {
			ret.addMensajeError("Ya existe una persona con ese DNI.");
			ret.setHayError();
		}
		if(!ValidadoresUsuario.validarAltaUsuario(usuario)) {
			ret.addMensajeError("Ya existe un usuario con ese MAIL.");
			ret.setHayError();
		}
		return ret;
		
	}
	
	public static boolean validarBorrarPersona() {
		return true;
	}

}
