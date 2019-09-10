package vdg.controller.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioControllerPrueba {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
		
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepo.findAll();
	}
	
}
