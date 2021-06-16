package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import application.RandomPassword;
import application.Util;
import controller.listener.PhysicalClientListener;
import model.City;
import model.Person;
import model.Phone;
import model.PhysicalClient;
import model.Profile;
import model.Sex;
import model.State;
import model.User;
import repository.CityRepository;
import repository.StateRepository;

@Named @ViewScoped
public class PhysicalClientController extends Controller<Person> {

	private static final long serialVersionUID = -1443526557455688665L;

	private String search, state;
	private List<PhysicalClient> listPhysicalClient;
	private Phone phone;
	private boolean skip;

	public void openClientListener() {
		PhysicalClientListener listener = new PhysicalClientListener();
		listener.open();
	}

	public void getPhysicalClientListener(SelectEvent event) {
		clear();

		if (event.getObject().getClass() != PhysicalClient.class)
			Util.addMessageError("Tipo de cliente inválido!");
		else {
			PhysicalClient entity = (PhysicalClient) event.getObject();
			setEntity(entity);
		}
	}

	private void createNewUser() {
		// TODO encaminhar senha criada para email
		getEntity().getUser().setPassword(RandomPassword.generatePassword(8));
		getEntity().getUser().setProfile(Profile.CLIENTE);
	}

	public Sex[] getListSex() {
		return Sex.values();
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

		// TODO descobrir porque não atualiza o USER
		super.save();
	}

	@Override
	public void clear() {
		super.clear();
	}

	@Override
	public PhysicalClient getEntity() {
		if (entity == null) {
			entity = new PhysicalClient();
			entity.setUser(new User());
		}
		return (PhysicalClient) entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Phone getPhone() {
		if (phone == null)
			phone = new Phone();
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public List<PhysicalClient> getListPhysicalClient() {
		if (listPhysicalClient == null)
			listPhysicalClient = new ArrayList<PhysicalClient>();
		return listPhysicalClient;
	}

}