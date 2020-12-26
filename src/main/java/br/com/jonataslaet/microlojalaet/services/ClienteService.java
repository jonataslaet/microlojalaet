package br.com.jonataslaet.microlojalaet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository cr;

	public Cliente buscar(Integer id) {
		Optional<Cliente> categoria = cr.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Cliente.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
}
