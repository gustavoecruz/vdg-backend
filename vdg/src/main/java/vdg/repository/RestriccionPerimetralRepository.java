package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.RestriccionPerimetral;

public interface RestriccionPerimetralRepository extends Repository<RestriccionPerimetral, Integer>{
	
	public List<RestriccionPerimetral> findAll();
	public RestriccionPerimetral save(RestriccionPerimetral restriccionPerimetral);
	public void delete(RestriccionPerimetral restriccionPerimetral);
	public List<RestriccionPerimetral> findByIdUsuarioAdministrativo(int idUsuarioAdministrativo);
	public RestriccionPerimetral findByIdUsuarioVictimario(int idPersona);
	public RestriccionPerimetral findByIdUsuarioDamnificada(int idPersona);
}
