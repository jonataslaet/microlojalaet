package br.com.jonataslaet.microlojalaet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.domain.Produto;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;
import br.com.jonataslaet.microlojalaet.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Produto buscar(Integer id) {
		Optional<Produto> categoria = produtoRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Produto.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(nome, categorias, pageRequest);
	}
}
