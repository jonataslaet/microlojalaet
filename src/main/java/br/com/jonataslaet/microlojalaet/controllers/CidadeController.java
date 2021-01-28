package br.com.jonataslaet.microlojalaet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Cidade;
import br.com.jonataslaet.microlojalaet.services.CidadeService;

@RestController
@RequestMapping(value="/estados/{idEstado}/cidades")
public class CidadeController {

	@Autowired
	CidadeService cidadeService;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	ResponseEntity<?> find(@PathVariable Integer idEstado, @PathVariable Integer id) {
		Cidade cidade = cidadeService.find(idEstado, id);
		return ResponseEntity.ok().body(cidade);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<CidadeDTO>> findAll(@PathVariable Integer idEstado) {
		List<CidadeDTO> cidadesDTO = cidadeService.findAllByEstado(idEstado);
		return ResponseEntity.ok().body(cidadesDTO);
	}
	
}
