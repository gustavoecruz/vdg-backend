package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.domain.Infraccion;
import vdg.repository.InfraccionRepositoy;

@RestController
@RequestMapping("/Infraccion")
@CrossOrigin
public class InfraccionController {
	
	@Autowired
	private InfraccionRepositoy infraccionRepo;
	
	@GetMapping
	public List<Infraccion> listar(){
		return infraccionRepo.findAll();
	}
	
	@GetMapping("/{idRestriccion}")
	public List<Infraccion> listarPorRestriccion(@PathVariable("idRestriccion") int idRestriccion){
		return infraccionRepo.findByIdRestriccion(idRestriccion);
	}
	
	@PostMapping
	public Infraccion agregar(@RequestBody Infraccion infraccion) {
		return infraccionRepo.save(infraccion);
	}
	
	public Infraccion getUltimaInfraccion(int idRestriccion) {
		return infraccionRepo.getUltimaInfraccion(idRestriccion);
	}

}
