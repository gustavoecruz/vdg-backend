package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import vdg.model.domain.Usuario;

public interface UsuarioRepository extends Repository<Usuario, Integer>{
	
	public List<Usuario> findAll();
	public Usuario save(Usuario usuario);
	public void delete(Usuario usuario);
	public List<Usuario> findByEmail(String email);

}
