package br.com.jonataslaet.microlojalaet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonataslaet.microlojalaet.domain.Pedido;
import br.com.jonataslaet.microlojalaet.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	PedidoService cs;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	ResponseEntity<?> buscar(@PathVariable Integer id) {
		Pedido pedido = cs.buscar(id);
		return ResponseEntity.ok().body(pedido);
	}
}
