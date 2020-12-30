package br.com.jonataslaet.microlojalaet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.ClienteDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.DataIntegrityException;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository cr;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = cr.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Cliente.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
	
	public ResponseEntity<Object> update(ClienteDTO clienteAtualDTO, Integer id) {
		Cliente cliente = find(id);
		updateData(clienteAtualDTO, cliente);
		cliente.setId(id);
		cr.save(cliente);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Object> delete(Integer id) {
		find(id);
		try {
			cr.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar uma Cliente que possua entidade associada a ele");
		}
		return ResponseEntity.noContent().build();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return cr.findAll(pageRequest);
	}
	
	private void updateData(ClienteDTO clienteAtualDTO, Cliente clienteAntigo) {
		clienteAntigo.setNome(clienteAtualDTO.getNome());
		clienteAntigo.setEmail(clienteAtualDTO.getEmail());
	}
}
