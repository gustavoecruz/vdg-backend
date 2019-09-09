package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;

public interface PersonaRepository extends Repository<Persona, Integer>{
	
	public List<Persona> findAll();
	public Persona save(Persona persona);
	public void delete(Persona persona);
}
