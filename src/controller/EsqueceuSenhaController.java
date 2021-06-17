package controller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

import application.Email;
import application.RepositoryException;
import application.Util;
import application.ValidateException;
import application.VersionException;
import model.RecuperarSenha;
import model.User;
import repository.Repository;
import repository.UserRepository;

@Named
@ViewScoped
public class EsqueceuSenhaController extends Controller<RecuperarSenha> {
	private static final long serialVersionUID = 8893135762115295270L;
	
	private String email;

	@Override
	public RecuperarSenha getEntity() {
		if (entity == null) {
			entity = new RecuperarSenha();
		}
		return entity;
	}
	
	public void enviar() throws RepositoryException, ValidateException, VersionException {
		UserRepository repo = new UserRepository();
		User user = null;
		try {
			user = (User) repo.findByEmail(getEmail());
			
		} catch (NoResultException e) {
			e.printStackTrace();
			Util.addMessageError("Não foi encontrado esse email no sistema.");
			return;
		}
		
		double valor1 = Math.random() * 1000;
		double valor2 = Math.random() * 1000;
		
		DecimalFormat format = new DecimalFormat("000");
		String codigo = "H - " + format.format(valor1)+" - "+format.format(valor2);
		
		Email email = new Email(getEmail(), "Solicitação de alteração de senha", "Informe o seguinte código: " + codigo);
		getEntity().setCodigo(codigo);
		getEntity().setUser(user);
		LocalDateTime dataLimite = LocalDateTime.now();
		getEntity().setDataLimite(dataLimite.plusDays(1));
		getEntity().setUtilizado(false);
		
		if (salvarNovo()) {
			if (email.enviar() ) {
				Util.redirect("verificarCodigo.xhtml");
			} else {
				Util.addMessageError("Problemas ao enviar o código por email.");
			}
		} else {
			Util.addMessageError("Problemas ao salvar no banco. Tente novamente mais tarde.");
		}

	}
	
	public boolean salvarNovo() throws ValidateException, VersionException {
		Repository<RecuperarSenha> repo = new Repository<RecuperarSenha>();
	
		try {
			repo.beginTransaction();
			repo.save(getEntity());
			repo.commitTransaction();
			
			clear();
			return true;
		} catch (RepositoryException e) {
			repo.rollbackTransaction();
			return false;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
