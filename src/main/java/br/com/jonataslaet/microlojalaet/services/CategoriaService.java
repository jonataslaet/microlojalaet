package br.com.jonataslaet.microlojalaet.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.microlojalaet.controllers.CategoriaDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.DataIntegrityException;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;

	public List<CategoriaDTO> findAll() {
		List<CategoriaDTO> categoriasDTO = cr.findAll().stream().map(CategoriaDTO::new).collect(Collectors.toList());
		return categoriasDTO;
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = cr.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Categoria.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}

	public ResponseEntity<Object> insert(CategoriaDTO categoriaDTO) {
		Categoria cat = fromDTO(categoriaDTO);
		cr.save(cat);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	public ResponseEntity<Object> update(CategoriaDTO categoriaAtualDTO, Integer id) {
		Categoria categoria = find(id);
		updateData(categoriaAtualDTO, categoria);
		categoria.setId(id);
		cr.save(categoria);
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
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cr.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void updateData(CategoriaDTO categoriaAtualDTO, Categoria categoriaAntigo) {
		categoriaAntigo.setNome(categoriaAtualDTO.getNome());
	}
}
