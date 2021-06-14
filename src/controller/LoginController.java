package controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import model.User;
import repository.UserRepository;

@Named @RequestScoped
public class LoginController {

	private String email;
	private String password;

	public String login() {
		UserRepository repository = new UserRepository();
		User user = (User) repository.validateLogin(email, password);
		
		if (user != null) {
			Session.getInstance().setAttribute("logUser", user);
			return "/index.xhtml?faces-redirect=true";
		}
		
		Util.addMessageError("Login ou Senha inválido.");
		return "";
	}
	
	public void forgotPassword() {
		// TODO implementar
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}