package vdg.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import vdg.model.controladorUbicaciones.Ubicacion;

public interface UbicacionRepository extends Repository<Ubicacion, Integer>{
	
	public List<Ubicacion> findAll();
	public Ubicacion save(Ubicacion ubicacion);
	public void delete(Ubicacion ubicacion);
	public Ubicacion findByIdPersona(int idPersona);
	@Query(value = "SELECT * FROM Ubicacion u WHERE u.fecha<=?1", nativeQuery = true)
	public List<Ubicacion> findIlocalizables(Timestamp fechaLimite);
	
}
