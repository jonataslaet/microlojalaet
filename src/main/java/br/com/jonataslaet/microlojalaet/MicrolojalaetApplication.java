package br.com.jonataslaet.microlojalaet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.jonataslaet.microlojalaet.services.S3Service;

@SpringBootApplication
public class MicrolojalaetApplication implements CommandLineRunner{

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(MicrolojalaetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		s3Service.uploadFile("/home/maida/Imagens/eumesmonoaviao.jpeg");
	}

}
