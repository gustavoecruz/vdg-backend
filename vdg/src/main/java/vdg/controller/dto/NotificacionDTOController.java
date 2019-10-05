package vdg.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vdg.controller.IncidenciaController;
import vdg.controller.UsuarioController;
import vdg.model.domain.Incidencia;
import vdg.model.domain.Notificacion;
import vdg.model.domain.Usuario;
import vdg.model.dto.NotificacionDTO;
import vdg.repository.NotificacionRepository;

@RestController
@RequestMapping("/Notificacion")
@CrossOrigin
public class NotificacionDTOController {
	
	@Autowired
	private IncidenciaController incidenciaController;
	
	@Autowired
	private NotificacionRepository notificacionRepo;

	@Autowired
	private UsuarioController usuarioController;
	
	@GetMapping("/Usuario/{id}")
	public List<Notificacion> listar(@PathVariable("id") int idUsuario){
		return notificacionRepo.findByIdUsuario(idUsuario);
	}
	/*
	@GetMapping("/{email}")
	public List<NotificacionDTO> getByIdUsuario(@PathVariable("email") String email){
		//TOMO EL USUARIO QUE HACE LA CONSULTA
		Usuario usuario = usuarioController.findByEmail(email);
		//TRAIGO LAS NOTIFICACIONES DE ESE USUARIO
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		notificaciones = notificacionRepo.findByIdUsuario(usuario.getIdUsuario());
		//GENERO LAS NOTIFICACIONES DTO
		List<NotificacionDTO> retNoti = new ArrayList<NotificacionDTO>();
		retNoti = generarNotificacionesDTO(notificaciones);
		
		return retNoti;
	}
	
	public List<NotificacionDTO> generarNotificacionesDTO(List<Notificacion> notificaciones) {
		
		List<NotificacionDTO> notificacionesDTO = new ArrayList<NotificacionDTO>();
		Incidencia incidencia;
		
		for(Notificacion notificacion : notificaciones) {
			//TRAIGO LA INCIDENCIA DE ESA NOTIFICACION Y LA GUARDO EN LA NOTI DTO
			incidencia = incidenciaController.getById(notificacion.getIdIncidencia());
			NotificacionDTO notificacionDTO = new NotificacionDTO(notificacion, incidencia);
			
			notificacionesDTO.add(notificacionDTO);
		}
		
		return notificacionesDTO;
	}
*/
}
