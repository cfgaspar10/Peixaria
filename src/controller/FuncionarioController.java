package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Util;
import model.Funcionario;
import model.Perfil;
import model.PessoaFisica;
import repository.FuncionarioRepository;
import repository.Repository;

@Named
@ViewScoped
public class FuncionarioController extends Controller<Funcionario> {
	private static final long serialVersionUID = -8050082129405633174L;

	public FuncionarioController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		entity = (Funcionario) flash.get("editarFuncionario");
	}

	private List<Funcionario> listaFuncionario;

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario();
			entity.setPessoaFisica(new PessoaFisica());
		}
		return entity;
	}

	public String redirecionar() {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		return "crudFuncionario.xhtml?faces-redirect=true";
	}

	public List<Funcionario> getListaFuncionario() {
		FuncionarioRepository fr = new FuncionarioRepository();
		if (listaFuncionario == null) {
			try {
				listaFuncionario = fr.findAll();
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<>();
			}
		}
		return listaFuncionario;
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public void edite(Funcionario funcionario) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("editarFuncionario", funcionario);
		Util.redirect("cadastroFuncionario.xhtml");
	}

	public void excluir(Funcionario entity) {
		Repository<Funcionario> repo = new Repository<Funcionario>();
		System.out.println(entity);
		try {
			repo.beginTransaction();
			repo.delete(entity);
			repo.commitTransaction();
			listaFuncionario = null;
			Util.addMessageInfo("Funcionario removido com sucesso.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addMessageError("Erro ao remover o Funcionario.");
		} finally {
			clear();
		}
	}
}
