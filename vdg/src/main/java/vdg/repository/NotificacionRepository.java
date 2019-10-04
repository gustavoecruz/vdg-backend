package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Notificacion;

public interface NotificacionRepository extends Repository<Notificacion, Integer>{

	public List<Notificacion> findByIdUsuario(int idUsuario);
	public Notificacion save(Notificacion notificacion);
}
