package br.com.jonataslaet.microlojalaet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Produto;
import br.com.jonataslaet.microlojalaet.services.ProdutoService;
import br.com.jonataslaet.microlojalaet.services.validations.utils.URL;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	ResponseEntity<?> buscar(@PathVariable Integer id) {
		Produto pedido = produtoService.buscar(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<Page<ProdutoDTO>> findEachPage(
			@RequestParam (value = "produto", defaultValue = "") String nomeProduto, 
			@RequestParam (value = "categorias", defaultValue = "0") String nomeCategorias, 
			@RequestParam (value = "page", defaultValue = "0") Integer page, 
			@RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam (value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam (value = "direction", defaultValue = "DESC") String direction) {
		String decodedProductName = URL.decodeParam(nomeProduto);
		List<Integer> idsDasCategorias = URL.decodificaStringNumaListaDeInteiros(nomeCategorias);
		Page<Produto> produtos = produtoService.search(decodedProductName, idsDasCategorias, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> produtosDTO = produtos.map(produto -> new ProdutoDTO(produto));
		return ResponseEntity.ok().body(produtosDTO);
	}
}
