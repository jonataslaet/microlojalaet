package br.com.jonataslaet.microlojalaet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Estado;
import br.com.jonataslaet.microlojalaet.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {

	@Autowired
	EstadoService estadoService;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	ResponseEntity<?> find(@PathVariable Integer id) {
		Estado estado = estadoService.find(id);
		return ResponseEntity.ok().body(estado);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<EstadoDTO>> findAll() {
		List<EstadoDTO> estadosDTO = estadoService.findAll();
		return ResponseEntity.ok().body(estadosDTO);
	}
}
