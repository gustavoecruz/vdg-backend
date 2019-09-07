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

import vdg.model.Direccion;
import vdg.repository.DireccionRepository;

@RestController
@RequestMapping("/Direccion")
public class DireccionController {
	
	@Autowired
	private DireccionRepository direccionRepo;
		
	@GetMapping
	public List<Direccion> listar(){
		return direccionRepo.findAll();
	}
	
	@PostMapping
	public Direccion agregar(@RequestBody Direccion direccion){
		return direccionRepo.save(direccion);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		Direccion d = new Direccion();
		d.setIdDireccion(id);
		direccionRepo.delete(d);
	}


}
