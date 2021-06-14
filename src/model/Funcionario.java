package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import model.validator.Validator;

@Entity
public class Funcionario extends DefaultEntity<Funcionario>{
	

	private static final long serialVersionUID = 2131368692010291911L;

	@Column(length = 30)
	private String senha;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PessoaFisica pessoaFisica;
	
	private Perfil perfil;
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
	public Funcionario getClone() {
		try {
			return (Funcionario) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Validator<Funcionario> getValidator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
