package br.com.jonataslaet.microlojalaet.services;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.domain.EstadoPagamento;
import br.com.jonataslaet.microlojalaet.domain.ItemPedido;
import br.com.jonataslaet.microlojalaet.domain.PagamentoComBoleto;
import br.com.jonataslaet.microlojalaet.domain.Pedido;
import br.com.jonataslaet.microlojalaet.repositories.ItemPedidoRepository;
import br.com.jonataslaet.microlojalaet.repositories.PagamentoRepository;
import br.com.jonataslaet.microlojalaet.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	PagamentoService pagamentoService;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EmailService emailService;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Pedido.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}

	public ResponseEntity<Object> insert(Pedido pedido) {
		
		Cliente cliente = clienteService.find(pedido.getCliente().getId());
		
		pedido.setCliente(cliente);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE.getCodigo());
		pedido.getPagamento().setPedido(pedido);
		
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoEncontrado = (PagamentoComBoleto) pedido.getPagamento();
			pagamentoService.preencherDataVencimento(pagamentoEncontrado, pedido.getInstante());
		}
		
		pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.buscar(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
}
