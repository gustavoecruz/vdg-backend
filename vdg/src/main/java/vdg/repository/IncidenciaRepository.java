package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import vdg.model.domain.Incidencia;

public interface IncidenciaRepository extends Repository<Incidencia, Integer>{
	
	public List<Incidencia> findByIdRestriccion(int idRestriccion);
	public Incidencia save(Incidencia incidencia);
	public void delete(Incidencia incidencia);
	
	

}
