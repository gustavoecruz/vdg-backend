package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.controladorUbicaciones.Ubicacion;

public interface UbicacionRepository extends Repository<Ubicacion, Integer>{
	
	public List<Ubicacion> findAll();
	public Ubicacion save(Ubicacion ubicacion);
	public void delete(Ubicacion ubicacion);
	public Ubicacion findByIdPersona(int idPersona);
}
