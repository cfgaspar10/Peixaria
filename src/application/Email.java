package application;

import java.io.InputStream;
import java.util.Properties;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Email {
	private String usuario;
	private String senha;
	private String emailDestino;
	private String assunto;
	private String mensagem;

	public Email(String emailDestino, String assunto, String mensagem) {
		Properties prop = new Properties();
		try (InputStream is = this.getClass().getResourceAsStream("/config/email.properties")) {
			prop.load(is);
		} catch (Exception e) {
			System.out.println("\n\n\n DEVE CRIAR O ARQUIVO (email.properties) EM (src/config) \n\n\n");
		}

		setUsuario(prop.getProperty("usuario"));
		setSenha(prop.getProperty("senha"));
		setEmailDestino(emailDestino);
		setAssunto(assunto);
		setMensagem(mensagem);
	}

	public boolean enviar() {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, 
				new jakarta.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getUsuario(), getSenha());
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getUsuario()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(getEmailDestino()));
			message.setSubject(getAssunto());
			message.setText(getMensagem());

			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}		
	}

	private String getUsuario() {
		return usuario;
	}
	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	private String getSenha() {
		return senha;
	}
	private void setSenha(String senha) {
		this.senha = senha;
	}
	private String getEmailDestino() {
		return emailDestino;
	}
	private void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	private String getAssunto() {
		return assunto;
	}
	private void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	private String getMensagem() {
		return mensagem;
	}
	private void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

