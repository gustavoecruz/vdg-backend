package vdg.controller;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.FotoIdentificacion;
import vdg.repository.FotoIdentificacionRepository;

@RestController
@RequestMapping("/FotoIdentificacion")
@CrossOrigin
public class FotoIdentificacionController {

	@Autowired
	private FotoIdentificacionRepository fotoIdentificacionRepo;

	@GetMapping
	public List<FotoIdentificacion> listar() {
		return fotoIdentificacionRepo.findAll();
	}

	@GetMapping("{idPersona}")
	public FotoIdentificacion getFotoIdentificacionPersona(@PathVariable("idPersona") int idPersona) {
		return fotoIdentificacionRepo.findByIdPersona(idPersona);
	}

	@PostMapping
	public FotoIdentificacion agregar(String foto, int idPersona) {
		Decoder decoder = Base64.getDecoder();
		byte[] decodedByte = decoder.decode(foto.split(",")[1]);
		FotoIdentificacion fotoIdentificacion = new FotoIdentificacion();
		fotoIdentificacion.setIdPersona(idPersona);
		fotoIdentificacion.setFoto(decodedByte);
		return fotoIdentificacionRepo.save(fotoIdentificacion);
	}
	
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable("id") int idPersona) {
		fotoIdentificacionRepo.deleteByIdPersona(idPersona);
	}

}
