package br.com.jonataslaet.microlojalaet.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@RequestMapping(method = RequestMethod.GET)
	List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();

		Categoria cat1 = new Categoria(1, "Escritório");
		Categoria cat2 = new Categoria(2, "Informática");

		categorias.add(cat1);
		categorias.add(cat2);

		return categorias;
	}
}
