package model.validator;

import application.ValidateException;

public interface Validator <T> {
	
	public void validate(T entity) throws ValidateException;

}