package br.com.jonataslaet.microlojalaet.config;

import java.text.ParseException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.jonataslaet.microlojalaet.services.DBService;
import br.com.jonataslaet.microlojalaet.services.EmailService;
import br.com.jonataslaet.microlojalaet.services.MockMailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBService dbService;

	@Bean
	public boolean instanciaDatabase() throws ParseException {
		return dbService.instanciaDataBase();
	}
	
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
	
	@Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(25);

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.ssl.enable", "true");
        properties.setProperty("mail.debug", "false");
        
        return properties;
    }
}
