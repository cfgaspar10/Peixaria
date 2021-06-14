package model.validator;

import java.time.LocalDate;

import application.ValidateException;
import model.Employee;
import model.Person;
import repository.EmployeeRepository;

public class EmployeeValidator implements Validator<Person> {

	@Override
	public void validate(Person entity) throws ValidateException {
		validateCpf((Employee) entity);
		validateBirthDate((Employee) entity);
	}
	
	private void validateCpf(Employee entity) throws ValidateException {
		EmployeeRepository repository = new EmployeeRepository();
		if (repository.contains(entity.getId(), entity.getCpf())) {
			throw new ValidateException("Cpf Inválido. Este cpf já está sendo utilizado por outro funcionário.");
		}
	}

	private void validateBirthDate(Employee entity) throws ValidateException {
		LocalDate data = new java.sql.Date(entity.getBirthDate().getTime()).toLocalDate(); 
		LocalDate dataLimite = 	LocalDate.now().minusYears(14);

		if (data.isAfter(dataLimite)) {
			throw new ValidateException("Data Inválida. O funcionário não pode ter menos de 14 anos.");
		}
	}

}