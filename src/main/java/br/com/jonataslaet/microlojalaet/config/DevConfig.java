package br.com.jonataslaet.microlojalaet.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.jonataslaet.microlojalaet.services.DBService;
import br.com.jonataslaet.microlojalaet.services.EmailService;
import br.com.jonataslaet.microlojalaet.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	String strategy;

	@Bean
	public boolean instanciaDatabase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false;
		}
		
		return dbService.instanciaDataBase();
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
