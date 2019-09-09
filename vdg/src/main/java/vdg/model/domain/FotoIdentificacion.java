package vdg.model.domain;

import java.sql.Blob;

import javax.persistence.*;

@Entity
@Table(name = "FotoIdentificacion")
public class FotoIdentificacion {
	
	@Id
	@Column
	private int idFoto;

	@Column
	private Blob foto;

	@Column
	private int idPersona;

	public FotoIdentificacion() {
		
	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public Blob getFoto() {
		return foto;
	}

	public void setFoto(Blob foto) {
		this.foto = foto;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}	
	
}
