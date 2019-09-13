package vdg.model.validadores;

import org.springframework.beans.factory.annotation.Autowired;
import vdg.controller.UsuarioController;
import vdg.model.domain.Usuario;

public class ValidadoresUsuario {

	@Autowired
	private static UsuarioController usuarioController = new UsuarioController();
	
	public static boolean validarAltaUsuario(Usuario usuario) {
		
		return existeUsuario(usuario);
	}

	public static boolean validarBajaUsuario() {
		return true;
	}

	private static boolean existeUsuario(Usuario usuario) {
		return usuarioController.findByEmail(usuario.getEmail()) != null;
	}
}
