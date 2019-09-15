package vdg.model.dto;

import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Usuario;

public class RestriccionDTO {

	private Persona damnificada;
	private Persona victimario;
	private Usuario usuario;
	private RestriccionPerimetral restriccion;

	public RestriccionDTO(Persona damnificada, Persona victimario, Usuario usuario, RestriccionPerimetral restriccion) {
		this.damnificada = damnificada;
		this.victimario = victimario;
		this.usuario = usuario;
		this.restriccion = restriccion;
	}

	public Persona getDamnificada() {
		return damnificada;
	}

	public void setVictima(Persona damnificada) {
		this.damnificada = damnificada;
	}

	public Persona getVictimario() {
		return victimario;
	}

	public void setVictimario(Persona victimario) {
		this.victimario = victimario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RestriccionPerimetral getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(RestriccionPerimetral restriccion) {
		this.restriccion = restriccion;
	}

	public void setDamnificada(Persona damnificada) {
		this.damnificada = damnificada;
	}
	
}
