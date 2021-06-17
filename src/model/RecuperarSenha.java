package model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.validator.Validator;

@Entity
public class RecuperarSenha extends DefaultEntity<RecuperarSenha>{


	private static final long serialVersionUID = -3493349531818260786L;
	
	private String codigo;
	private LocalDateTime dataLimite;
	private boolean utilizado;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	private User user;



	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public LocalDateTime getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(LocalDateTime dataLimite) {
		this.dataLimite = dataLimite;
	}

	public boolean isUtilizado() {
		return utilizado;
	}

	public void setUtilizado(boolean utilizado) {
		this.utilizado = utilizado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public Validator<RecuperarSenha> getValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
