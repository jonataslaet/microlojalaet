package br.com.jonataslaet.microlojalaet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService cs;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria categoria = cs.find(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> insert(@RequestBody Categoria categoria) {
		return cs.insert(categoria);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	ResponseEntity<?> update(@RequestBody Categoria categoria, @PathVariable Integer id) {
		return cs.update(categoria, id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Integer id) {
		return cs.delete(id);
	}
}
