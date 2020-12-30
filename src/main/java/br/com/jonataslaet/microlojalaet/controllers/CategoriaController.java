package br.com.jonataslaet.microlojalaet.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService cs;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> categoriasDTO = cs.findAll();
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	ResponseEntity<Page<CategoriaDTO>> findEachPage(
			@RequestParam (value = "page", defaultValue = "0") Integer page, 
			@RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam (value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam (value = "direction", defaultValue = "DESC") String direction) {
		Page<Categoria> categorias = cs.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> categoriasDTO = categorias.map(categoria -> new CategoriaDTO(categoria));
		return ResponseEntity.ok().body(categoriasDTO);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria categoria = cs.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> insert(@Valid @RequestBody CategoriaDTO categoria) {
		return cs.insert(categoria);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	ResponseEntity<?> update(@Valid @RequestBody CategoriaDTO categoria, @PathVariable Integer id) {
		return cs.update(categoria, id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Integer id) {
		return cs.delete(id);
	}
}
