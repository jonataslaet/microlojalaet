package br.com.jonataslaet.microlojalaet.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.microlojalaet.controllers.exceptions.DataIntegrityException;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;

	public Categoria find(Integer id) {
		Optional<Categoria> categoria = cr.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Categoria.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}

	public ResponseEntity<Object> insert(Categoria categoria) {
		cr.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<Object> update(Categoria categoriaAtual, Integer id) {
		find(id);
		categoriaAtual.setId(id);
		cr.save(categoriaAtual);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Object> delete(Integer id) {
		find(id);
		try {
			cr.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar uma Categoria que possua Produto associado a ela");
		}
		return ResponseEntity.noContent().build();
	}
}
