package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Localidad;

public interface LocalidadRepository extends Repository<Localidad, Integer>{
	
	public List<Localidad> findAll();
	public Localidad save(Localidad localidad);
	public void delete(Localidad localidad);
}