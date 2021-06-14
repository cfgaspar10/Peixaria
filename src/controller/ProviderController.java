package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import application.RandomPassword;
import controller.listener.ProviderListener;
import model.City;
import model.Person;
import model.Phone;
import model.Profile;
import model.Provider;
import model.Situation;
import model.State;
import model.User;
import repository.CityRepository;
import repository.StateRepository;

@Named @ViewScoped
public class ProviderController extends Controller<Person> {

	private static final long serialVersionUID = 3679724066632322580L;

	private String search, state;
	private List<Provider> listProvider;
	private Phone phone;
	private boolean skip;

	public void openProviderListener() {
		ProviderListener listener = new ProviderListener();
		listener.open();
	}

	public void getProviderListener(SelectEvent event) {
		clear();
		Provider entity = (Provider) event.getObject();
		setEntity(entity);
	}

	private void createNewUser() {
		// TODO encaminhar senha criada para email
		getEntity().getUser().setPassword(RandomPassword.generatePassword(8));
		getEntity().getUser().setProfile(Profile.CLIENTE);
	}

	public Situation[] getListSituation() {
		return Situation.values();
	}
	
	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false;
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}
	
	public List<String> getStateListener() {
		StateRepository repository = new StateRepository();
		List<String> list = new ArrayList<String>();
		for (State state : repository.findByName("")) {
			list.add(state.getName());
		}
		return list;
	}

	// TODO Corrigir Erro de validação: o valor não é válido
	public List<City> getCityListener() {
		CityRepository repository = new CityRepository();
		return repository.findByState(getState());
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

		// TODO descobrir porque não faz merge no USER
		super.save();
	}

	@Override
	public Provider getEntity() {
		if (entity == null) {
			entity = new Provider();
			entity.setUser(new User());
		}
		return (Provider) entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Provider> getListProvider() {
		return listProvider;
	}

	public void setListProvider(List<Provider> listProvider) {
		this.listProvider = listProvider;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Phone getPhone() {
		if (phone == null)
			phone = new Phone();
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

}