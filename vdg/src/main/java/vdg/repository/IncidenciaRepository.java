package vdg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import vdg.model.domain.Incidencia;

public interface IncidenciaRepository extends Repository<Incidencia, Integer>{
	
	public List<Incidencia> findByIdRestriccion(int idRestriccion);
	public Incidencia save(Incidencia incidencia);
	public void delete(Incidencia incidencia);
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=?1 and (i.topico='VictimarioIlocalizable' "
			+ "or i.topico='DamnificadaIlocalizable') ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getIlocalizable(int idRestriccion);
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=?1 and i.topico='DamnificadaIlocalizable' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getDamnificadaIlocalizable(int idRestriccion);
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=?1 and i.topico='VictimarioIlocalizable' ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getVictimarioIlocalizable(int idRestriccion);
	
	public Incidencia findByIdIncidencia(int idIncidencia);
	
	@Query(value = "SELECT * FROM Incidencia i WHERE i.idRestriccion=?1 ORDER BY i.fecha DESC", nativeQuery = true)
	public List<Incidencia> getUltimaIncidencia(int idRestriccion);
}
