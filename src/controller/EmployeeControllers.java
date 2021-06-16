//package controller;
//
////import java.util.ArrayList;
////import java.util.List;
////
////import javax.faces.view.ViewScoped;
////import javax.inject.Named;
////
////import org.primefaces.event.FlowEvent;
////import org.primefaces.event.SelectEvent;
////
////import application.Util;
////import controller.listener.FuncionarioListing;
////import model.Telefone;
////import model.TipoFuncionario;
////import model.Usuario;
//
//
//
////@Named @ViewScoped
//public class EmployeeControllers extends Controller<Usuario> {
//
////	private static final long serialVersionUID = 6912924793629925657L;
////
////	private String filtro;
//////	private Funcionario funcionario;
////
////	public void abrirFuncionarioListing() {
////		FuncionarioListing listing = new FuncionarioListing();
////		listing.open();
////	}
////
////	public void obterFuncionarioListing(SelectEvent event) {
////		Funcionario entity = (Funcionario) event.getObject();
////		setEntity(entity);
////
////	}
////
////	@Override
////	public Funcionario getEntity() {
////		if (entity == null) {
////			entity = new Funcionario();
////			entity.setTelefone(new Telefone());
////		}
////		return (Funcionario) entity;
////	}
////
////	public String getFiltro() {
////		return filtro;
////	}
////
////	public void setFiltro(String filtro) {
////		this.filtro = filtro;
////	}
////
////	public TipoFuncionario[] getListaTipoFuncionario() {
////		return TipoFuncionario.values();
////	}
////
////	@Override
////	public void save() {
////		getEntity().setSenha(Util.hashSHA256(getEntity().getSenha()));
////		super.save();
////
////	}
//
//	
//}