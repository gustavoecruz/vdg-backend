package vdg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vdg.model.domain.Notificacion;
import vdg.repository.NotificacionRepository;

@RestController
@RequestMapping("/Notificacion")
@CrossOrigin
public class NotificacionController {

	@Autowired
	private NotificacionRepository notificacionRepo;

	

	@GetMapping("/{idUsuario}")
	public List<Notificacion> listar(@PathVariable("idUsuario") int idUsuario) {
		return notificacionRepo.findByIdUsuario(idUsuario);
	}
	
	@GetMapping("/getNotificaciones/{idUsuario}")
	public List<Notificacion> getNotificaciones(@PathVariable("idUsuario") int idUsuario) {
		return notificacionRepo.findNotificacion(idUsuario);
	}
	
	@GetMapping("/getArchivadas/{idUsuario}")
	public List<Notificacion> getArchivadas(@PathVariable("idUsuario") int idUsuario) {
		return notificacionRepo.findArchivada(idUsuario);
	}
}
