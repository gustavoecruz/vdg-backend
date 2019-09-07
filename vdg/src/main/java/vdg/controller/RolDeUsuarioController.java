package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.RolDeUsuario;
import vdg.model.Usuario;
import vdg.repository.RolDeUsuarioRepository;
import vdg.repository.UsuarioRepository;

@RestController
@RequestMapping
public class RolDeUsuarioController {
	
	@Autowired
	private RolDeUsuarioRepository rolDeUsuarioRepo;
		
	@GetMapping
	public List<RolDeUsuario> listar(){
		return rolDeUsuarioRepo.findAll();
	}
	
	@PostMapping
	public RolDeUsuario agregar(@RequestBody RolDeUsuario rolDeUsuario){
		return rolDeUsuarioRepo.save(rolDeUsuario);
	}

}
