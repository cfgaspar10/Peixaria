package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import application.RandomPassword;
import controller.listener.ClientListener;
import model.Client;
import model.Person;
import model.Profile;
import model.Sex;
import model.User;

@Named @ViewScoped
public class ClientController extends Controller<Person> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search;
	private List<Client> listClient;
	private boolean userCreation;

	public void openClientListener() {
		ClientListener listener = new ClientListener();
		listener.open();
	}

	public void getClientListener(SelectEvent event) {
		clear();
		Client entity = (Client) event.getObject();
		setEntity(entity);
		renderEmailField();
	}

	public void renderEmailField() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() != null)
			userCreation = true;
		else if (getEntity().getUser() != null && getEntity().getUser().getId() == null) {
			if (getEntity().getUser().getEmail() != null)
				userCreation = !userCreation;
		}
		else if (getEntity().getUser() == null)
			userCreation = false;
	}

	public boolean disableToggle() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() != null) {
			userCreation = true;
			return true;
		}
		return false;
	}

	private void createNewUser() {
		// TODO encaminhar senha criada para email
		getEntity().getUser().setPassword(RandomPassword.generatePassword(8));
		getEntity().getUser().setProfile(Profile.CLIENTE);
	}

	public Sex[] getListSex() {
		return Sex.values();
	}

	@Override
	public void save() {
		if (getEntity().getUser() != null && getEntity().getUser().getId() == null) {
			String email = getEntity().getUser().getEmail();
			if (email != null && !email.isEmpty())
				createNewUser();
			else
				getEntity().setUser(null);
		}
		
		super.save();
	}
	
	@Override
	public void clear() {
		super.clear();
		userCreation = false;
	}

	@Override
	public Client getEntity() {
		if (entity == null) {
			entity = new Client();
			entity.setUser(new User());
		}
		return (Client) entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isUserCreation() {
		return userCreation;
	}

	public void setUserCreation(boolean userCreation) {
		this.userCreation = userCreation;
	}

	public List<Client> getListClient() {
		if (listClient == null)
			listClient = new ArrayList<Client>();
		return listClient;
	}

}