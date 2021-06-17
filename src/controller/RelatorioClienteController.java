package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Util;

import model.Client;
import repository.ClientRepository;

@Named
@ViewScoped
public class RelatorioClienteController implements Serializable {

	private static final long serialVersionUID = -6466793482965540067L;
	private String filtro;
	private List<Client> listClient;
	
	public void pesquisar() throws RepositoryException {
		ClientRepository repo = new ClientRepository();
		setListClient(repo.findByName(filtro));
	}
	
	public void limpar() {
		listClient = null;
	}
	
	public void gerarRelatorio() {
		Util.redirect("/Peixaria/clientereportservlet?NOME="+getFiltro());
//		Util.redirect("/Saude/pacientereportservlet");
	}


	public List<Client> getListaPaciente() {
		if (listClient == null)
			listClient = new ArrayList<Client>();
		return listClient;
	}

	public void setListClient(List<Client> listClient) {
		this.listClient = listClient;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
}