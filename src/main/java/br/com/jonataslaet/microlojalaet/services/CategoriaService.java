package br.com.jonataslaet.microlojalaet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;

	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = cr.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Categoria.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
}
