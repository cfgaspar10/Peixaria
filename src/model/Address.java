package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import model.validator.Validator;

@Entity
public class Address extends DefaultEntity<Address>{

	private static final long serialVersionUID = 205467836201825885L;
	
	@Column(nullable = false, length = 8)
	private String cep;
	
	@Column(nullable = false)
	private String street;
	
	@Column(nullable = false)
	private String number;
	
	@Column(length = 100)
	private String complement;
	
	@Column(nullable = false)
	private String neighborhood;
	
	@JoinColumn(nullable = false)
	private City city;

	public Address() {
		super();
	}
	
	@Override
	public Validator<Address> getValidator() {
		return null;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public City getCity() {
		if (city == null)
			city = new City();
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}