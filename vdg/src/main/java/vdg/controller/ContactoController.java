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

import vdg.model.domain.Contacto;
import vdg.repository.ContactoRepository;

@RestController
@RequestMapping("/Contacto")
@CrossOrigin
public class ContactoController {

	@Autowired
	private ContactoRepository contactoRepo;
	
	@GetMapping("/{idDamnificada}")
	public List<Contacto> getContactosDamnificada(@PathVariable("idDamnificada") int idDamnificada){
		return contactoRepo.findByIdDamnificada(idDamnificada);
	}
	
	@PostMapping
	public Contacto guardar(@RequestBody Contacto contacto) {
		return contactoRepo.save(contacto);
	}
	
	@DeleteMapping("/{idContacto}")
	public void eliminar(@PathVariable("idContacto") int idContacto) {
		Contacto contacto = new Contacto();
		contacto.setIdContacto(idContacto);
		contactoRepo.delete(contacto);
	}
	
}
