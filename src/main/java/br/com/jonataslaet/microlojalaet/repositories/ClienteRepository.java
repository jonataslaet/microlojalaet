package br.com.jonataslaet.microlojalaet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jonataslaet.microlojalaet.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	String findByEmail(String email);
	Boolean existsByEmail(String email);
	
	
}
