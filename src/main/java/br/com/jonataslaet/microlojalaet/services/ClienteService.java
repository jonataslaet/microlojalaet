package br.com.jonataslaet.microlojalaet.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonataslaet.microlojalaet.controllers.ClienteDTO;
import br.com.jonataslaet.microlojalaet.controllers.ClienteNewDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.AuthorizationException;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.DataIntegrityException;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Cidade;
import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.domain.Endereco;
import br.com.jonataslaet.microlojalaet.domain.Perfil;
import br.com.jonataslaet.microlojalaet.domain.TipoCliente;
import br.com.jonataslaet.microlojalaet.repositories.ClienteRepository;
import br.com.jonataslaet.microlojalaet.repositories.EnderecoRepository;
import br.com.jonataslaet.microlojalaet.security.UsuarioLogado;
import br.com.jonataslaet.microlojalaet.security.UsuarioSS;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	S3Service s3Service;

	public Cliente find(Integer id) {
		UsuarioSS usuario = UsuarioLogado.authenticated();
		if (usuario == null || !usuario.hasRole(Perfil.ADMIN) && !id.equals(usuario.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Cliente.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
	
	public ResponseEntity<Object> insert(ClienteNewDTO clienteNewDTO) {
		Cliente cliente = fromDTO(clienteNewDTO);
		clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	public ResponseEntity<Object> update(ClienteDTO clienteAtualDTO, Integer id) {
		Cliente cliente = find(id);
		updateData(clienteAtualDTO, cliente);
		cliente.setId(id);
		clienteRepository.save(cliente);
		return ResponseEntity.noContent().build();
	}

	public ResponseEntity<Object> delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar uma Cliente que possua entidade associada a ele");
		}
		return ResponseEntity.noContent().build();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	private void updateData(ClienteDTO clienteAtualDTO, Cliente clienteAntigo) {
		clienteAntigo.setNome(clienteAtualDTO.getNome());
		clienteAntigo.setEmail(clienteAtualDTO.getEmail());
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);
		Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo()),encoder.encode("blendo273"));
		Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteDTO.getTelefone1());
		
		if (clienteDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteDTO.getTelefone2());
		}
		if (clienteDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteDTO.getTelefone3());
		}
		return cliente;
	}
	
	public ResponseEntity<Object> uploadProfilePicture(MultipartFile profilePicture) {
		URI uri = s3Service.uploadFile(profilePicture);
		return ResponseEntity.created(uri).build();
	}
}
