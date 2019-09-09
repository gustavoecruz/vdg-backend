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

import vdg.model.domain.FotoIdentificacion;
import vdg.repository.FotoIdentificacionRepository;

@RestController
@RequestMapping("/FotoIdentificacion")
public class FotoIdentificacionController {
	
	@Autowired
	private FotoIdentificacionRepository fotoIdentificacionRepo;
		
	@GetMapping
	public List<FotoIdentificacion> listar(){
		return fotoIdentificacionRepo.findAll();
	}
	
	@PostMapping
	public FotoIdentificacion agregar(@RequestBody FotoIdentificacion fotoIdentificacion){
		return fotoIdentificacionRepo.save(fotoIdentificacion);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		FotoIdentificacion f = new FotoIdentificacion();
		f.setIdFoto(id);
		fotoIdentificacionRepo.delete(f);
	}


}
