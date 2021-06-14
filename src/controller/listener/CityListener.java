package controller.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.PrimeFaces;

import factory.JPAFactory;
import model.City;
import repository.CityRepository;

@Named @ViewScoped
public class CityListener extends Listener<City> {

	private static final long serialVersionUID = 247562187806265540L;
	
	private String search;
	private List<City> list;
	
	public void search() {
		CityRepository repository = new CityRepository();
		setList(repository.findByName(getSearch()));
	}
	
	public void select(int id) {
		EntityManager em = JPAFactory.getEntityManager();
		setEntity(em.find(City.class, id));
		
		PrimeFaces.current().dialog().closeDynamic(getEntity());
	}
	
	public void open() {
		Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", true);
        options.put("draggable", true);
        options.put("modal", true);
        options.put("width", 800);
        options.put("height", 500);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");  

        PrimeFaces.current().dialog().openDynamic("/pages/listener/cidade_lista", options, null);
	}

	@Override
	public City getEntity() {
		if (entity == null)
			entity = new City();
		return entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<City> getList() {
		if (list == null)
			list = new ArrayList<City>();
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}

}