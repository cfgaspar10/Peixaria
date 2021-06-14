package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import model.validator.Validator;

@Entity
public class PessoaFisica extends Persona {
	
	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 14)
	private String cpf;
	
	@Column(length = 50)
	private String sobrenome;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	@Override
	public Validator<Persona> getValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
