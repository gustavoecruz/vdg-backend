package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;

import vdg.controller.RestriccionPerimetralController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Usuario;

public class ValidadoresUsuario {

	@Autowired
	private static UsuarioController usuarioController = new UsuarioController();
	@Autowired
	private static RestriccionPerimetralController restriccionController = new RestriccionPerimetralController();
	
	public static boolean validarAltaUsuario(Usuario usuario) {
		return existeUsuario(usuario);
	}

	public static boolean validarBajaUsuario(Usuario usuario) {
		return tieneAsignadaRestriccion(usuario);
	}

	private static boolean existeUsuario(Usuario usuario) {
		return usuarioController.findByEmail(usuario.getEmail()) != null;
	}
	
	private static boolean tieneAsignadaRestriccion(Usuario usuario) {
		return !restriccionController.getByAdministrativo(usuario.getIdUsuario()).isEmpty();
	}
}
