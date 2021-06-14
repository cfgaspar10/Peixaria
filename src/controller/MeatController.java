package controller;

import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

import application.Util;
import controller.listener.MeatListener;
import model.Meat;
import model.MeatType;
import model.Product;

@Named @ViewScoped
public class MeatController extends Controller<Product> {

	private static final long serialVersionUID = 4570628857407841810L;

	private String search;
	private List<Meat> listMeat;

	public void openMeatListener() {
		MeatListener listener = new MeatListener();
		listener.open();
	}

	public void getMeatListener(SelectEvent event) {
		clear();
		
		if (event.getObject().getClass() != Meat.class)
			Util.addMessageError("Tipo de produto inválido!");
		else {
			Meat entity = (Meat) event.getObject();
			setEntity(entity);
		}
	}
	
	public MeatType[] getListMeatType() {
		return MeatType.values();
	}
	
	@Override
	public void save() {
		getEntity().setName(getEntity().getCut() + " - " + getEntity().getAnimal());
		
		super.save();
	}

	@Override
	public Meat getEntity() {
		if (entity == null)
			entity = new Meat();
		return (Meat) entity;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<Meat> getListMeat() {
		return listMeat;
	}

}