package vdg.controller;

import java.util.ArrayList;
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

import vdg.model.domain.RestriccionPerimetral;
import vdg.repository.RestriccionPerimetralRepository;

@RestController
@RequestMapping("/RestriccionPerimetral")
@CrossOrigin
public class RestriccionPerimetralController {
	
	@Autowired
	private RestriccionPerimetralRepository restriccionPerimetralRepo;
		
	@GetMapping
	public List<RestriccionPerimetral> listar(){
		return restriccionPerimetralRepo.findAll();
	}
	
	@PostMapping
	public RestriccionPerimetral agregar(@RequestBody RestriccionPerimetral restriccionPerimetral){
		return restriccionPerimetralRepo.save(restriccionPerimetral);
	}
	
	@DeleteMapping("/{id}")
	public void borrar(@PathVariable("id") int id) {
		RestriccionPerimetral r = new RestriccionPerimetral();
		r.setIdRestriccion(id);
		restriccionPerimetralRepo.delete(r);
	}
	
	@GetMapping("/{id}")
	public List<RestriccionPerimetral> getByAdministrativo(@PathVariable("id") int id){
		List<RestriccionPerimetral> todas = listar();
		List<RestriccionPerimetral> retorno = new ArrayList<RestriccionPerimetral>();
		for(RestriccionPerimetral restriccion : todas)
			if(restriccion.getIdRestriccion()==id)
				retorno.add(restriccion);
		
		return retorno;
	}

}
