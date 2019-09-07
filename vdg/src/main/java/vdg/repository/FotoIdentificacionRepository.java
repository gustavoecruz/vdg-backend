package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import vdg.model.FotoIdentificacion;

public interface FotoIdentificacionRepository extends Repository<FotoIdentificacion, Integer>{
	
	public List<FotoIdentificacion> findAll();
	public FotoIdentificacion save(FotoIdentificacion fotoIdentificacion);
	public void delete(FotoIdentificacion fotoIdentificacion);
}
