package vdg.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.Usuario;
import vdg.model.dto.ErrorDTO;
import vdg.model.logica.Encriptar;
import vdg.model.validadores.ValidadoresUsuario;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping("/Usuario")
@CrossOrigin
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepo;

	@GetMapping
	public List<Usuario> listar() {
		return usuarioRepo.findAll();
	}

	@PostMapping
	public Usuario agregar(@RequestBody Usuario usuario) {
		ErrorDTO error = new ErrorDTO();
		if (!ValidadoresUsuario.validarAltaUsuario(usuario)) {
			error.setHayError();
			error.addMensajeError("Ya existe un usuario creado con ese MAIL");
			//return error;
		}

		usuario.setContrasena(Encriptar.sha256(usuario.getContrasena()));
		//ENVIAR CONTRASEÑA POR MAIL
		//return error;
		return usuarioRepo.save(usuario);
	}

	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Usuario u = new Usuario();
		u.setIdUsuario(id);
		usuarioRepo.delete(u);
	}

	@GetMapping("/GetByEmail/{email}")
	public Usuario findByEmail(@PathVariable("email") String email) {
		List<Usuario> usuarios = usuarioRepo.findByEmail(email);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}

	@PostMapping("/login")
	public boolean login(@RequestBody Map<String, String> info) {

		Usuario user = findByEmail(info.get("email"));
		if (user == null)
			return false;
		if (user.getContrasena().equals(info.get("contrasena")))
			return true;
		return false;

	}

}
