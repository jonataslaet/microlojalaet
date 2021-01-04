package br.com.jonataslaet.microlojalaet.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {
	
	public static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	MailSender mailSender;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email...");
		mailSender.send(msg);
		LOG.info("Enviado o email");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Enviando html email...");
		javaMailSender.send(msg);
		LOG.info("Enviado o html email");
	}

}
