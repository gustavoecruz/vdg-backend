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

import vdg.model.RestriccionPerimetral;
import vdg.repository.RestriccionPerimetralRepository;

@RestController
@RequestMapping("/RestriccionPerimetral")
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


}
