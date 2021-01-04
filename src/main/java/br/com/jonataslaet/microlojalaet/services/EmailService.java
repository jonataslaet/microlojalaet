package br.com.jonataslaet.microlojalaet.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.jonataslaet.microlojalaet.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
}
