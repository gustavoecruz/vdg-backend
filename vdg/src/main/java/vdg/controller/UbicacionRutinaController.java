package vdg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.model.controladorRutina.UbicacionRutina;
import vdg.repository.UbicacionRutinaRepository;

@RestController
@RequestMapping("/UbicacionRutina")
@CrossOrigin
public class UbicacionRutinaController {
	
	@Autowired
	private UbicacionRutinaRepository ubicacionRutinaRepo;
	
	public List<UbicacionRutina> listar(){
		return ubicacionRutinaRepo.findAll();
	}
	
	public List<UbicacionRutina> getUbicacionesPersonaFecha(int idPersona, int dia){
		return ubicacionRutinaRepo.findByPersonaAndDia(idPersona, dia);
	}
	
}
