package br.com.jonataslaet.microlojalaet.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jonataslaet.microlojalaet.domain.Cliente;
import br.com.jonataslaet.microlojalaet.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService cs;
	
	@RequestMapping(value="{id}", method = RequestMethod.GET)
	ResponseEntity<?> buscar(@PathVariable Integer id) {
		Cliente cliente = cs.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(value="/email", method = RequestMethod.GET)
	ResponseEntity<?> buscarClientePorEmail(@RequestParam(name="value") String email) {
		Cliente cliente = cs.findByEmail(email);
		return ResponseEntity.ok().body(cliente);
	}
	

	@RequestMapping(value="/page", method = RequestMethod.GET)
	ResponseEntity<Page<ClienteDTO>> findEachPage(
			@RequestParam (value = "page", defaultValue = "0") Integer page, 
			@RequestParam (value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam (value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam (value = "direction", defaultValue = "DESC") String direction) {
		Page<Cliente> clientes = cs.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> clientesDTO = clientes.map(cliente -> new ClienteDTO(cliente));
		return ResponseEntity.ok().body(clientesDTO);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> insert(@Valid @RequestBody ClienteNewDTO cliente) {
		return cs.insert(cliente);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	ResponseEntity<?> update(@Valid @RequestBody ClienteDTO cliente, @PathVariable Integer id) {
		return cs.update(cliente, id);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	ResponseEntity<?> delete(@PathVariable Integer id) {
		return cs.delete(id);
	}
	
	@RequestMapping(value="/picture", method = RequestMethod.POST)
	ResponseEntity<?> uploadFile(@RequestParam(name="file") MultipartFile multipartFile) {
		return cs.uploadProfilePicture(multipartFile);
	}
}
