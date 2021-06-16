package model;

import javax.persistence.Column;
import javax.persistence.Entity;

import model.validator.EmployeeValidator;
import model.validator.Validator;

@Entity
public class Employee extends PhysicalPerson {


	private static final long serialVersionUID = 5620689547742431291L;

	@Column
	private String registry;
	
	@Column(nullable = false)
	private Situation situation;
	
	@Column(length = 30)
	private String password;
	
	public Employee() {
		super();
	}

	public Employee(String cpf, Sex sex) {
		super(cpf, sex);
	}
	
	@Override
	public Validator<Person> getValidator() {
		return new EmployeeValidator();
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}