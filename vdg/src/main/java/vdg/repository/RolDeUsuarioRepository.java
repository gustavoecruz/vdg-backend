package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import vdg.model.RolDeUsuario;

public interface RolDeUsuarioRepository extends Repository<RolDeUsuario, Integer>{
	
	public List<RolDeUsuario> findAll();
	public RolDeUsuario save(RolDeUsuario rolDeUsuario);
	public void delete(RolDeUsuario rolDeUsuario);
}
