package br.com.jonataslaet.microlojalaet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService cs;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	ResponseEntity<?> buscar(@PathVariable Integer id) {
		Cliente cliente = cs.buscar(id);
		return ResponseEntity.ok().body(cliente);
	}
}
