package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Infraccion;

public interface InfraccionRepositoy extends Repository<Infraccion, Integer> {
	
	public List<Infraccion> findAll();
	public List<Infraccion> findByIdRestriccion(int idRestriccion);
	public Infraccion save(Infraccion infraccion);
	
}
