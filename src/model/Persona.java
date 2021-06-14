package model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Persona extends DefaultEntity<Persona> {

	private static final long serialVersionUID = 1889242179774062399L;
	
	@Column(length = 50)
	private String nome;
	
	@Column
	private String email;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}