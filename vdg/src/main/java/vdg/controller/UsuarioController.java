package vdg.controller;

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
import vdg.model.domain.Usuario;
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
		return usuarioRepo.findByEmail(email).get(0);
	}

}
