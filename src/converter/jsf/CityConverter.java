package converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.City;
import repository.CityRepository;

@FacesConverter(forClass = City.class)
public class CityConverter implements Converter<City>{

	@Override
	public City getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty())
			return null;
		CityRepository repo = new CityRepository();
		return repo.findById(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, City value) {
		if (value == null || value.getId() == null)
			return null;
		
		return value.getId().toString();
	}

}