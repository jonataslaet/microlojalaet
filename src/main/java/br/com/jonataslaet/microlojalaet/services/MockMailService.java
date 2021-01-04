package br.com.jonataslaet.microlojalaet.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService {

	public static final Logger LOG = LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Simulado o envio de email");
	}

}
