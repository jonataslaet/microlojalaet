package br.com.jonataslaet.microlojalaet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Pedido;
import br.com.jonataslaet.microlojalaet.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository cr;

	public Pedido buscar(Integer id) {
		Optional<Pedido> categoria = cr.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Pedido.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
}
