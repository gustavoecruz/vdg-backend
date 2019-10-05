package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import vdg.model.domain.Notificacion;

public interface NotificacionRepository extends Repository<Notificacion, Integer>{

	public List<Notificacion> findByIdUsuario(int idUsuario);
	public Notificacion save(Notificacion notificacion);
	@Query(value = "SELECT * FROM Notificacion n WHERE n.idUsuario=?1 and (n.topico='Vista' or n.topico='NoVista') ORDER BY n.fecha DESC", nativeQuery = true)
	public List<Notificacion> findNotificacion(int idUsuario);
	@Query(value = "SELECT * FROM Notificacion n WHERE n.idUsuario=?1 and n.topico='Archivada' ORDER BY n.fecha DESC", nativeQuery = true)
	public List<Notificacion> findArchivada(int idUsuario);
}
