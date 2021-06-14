package model.validator;

import application.ValidateException;
import model.User;
import repository.UserRepository;

public class UserValidator implements Validator<User> {

	@Override
	public void validate(User entity) throws ValidateException {
		validateEmail(entity);
	}
	
	private void validateEmail(User entity) throws ValidateException {
		UserRepository repository = new UserRepository();
		if (repository.contains(entity.getId(), entity.getEmail())) {
			throw new ValidateException("Email Inválido. Este email já está sendo utilizado por outro usuário.");
		}
	}

}
