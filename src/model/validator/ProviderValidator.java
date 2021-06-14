package model.validator;

import application.ValidateException;
import model.Person;
import model.Provider;
import repository.ProviderRepository;

public class ProviderValidator implements Validator<Person> {

	@Override
	public void validate(Person entity) throws ValidateException {
		validateCnpj((Provider) entity);
	}

	private void validateCnpj(Provider entity) throws ValidateException {
		ProviderRepository repository = new ProviderRepository();
		if (repository.contains(entity.getId(), entity.getCnpj())) {
			throw new ValidateException("Cnpj Inválido. Este Cnpj já está sendo utilizado por outro fornecedor.");
		}
	}
	
}