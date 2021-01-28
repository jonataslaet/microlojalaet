package br.com.jonataslaet.microlojalaet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.EstadoDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Estado;
import br.com.jonataslaet.microlojalaet.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	EstadoRepository estadoRepository;
	
	public Estado find(Integer id) {
		
		Optional<Estado> estado = estadoRepository.findById(id);
		return estado.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Estado.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
	
	public List<EstadoDTO> findAll() {
		List<EstadoDTO> estadosDTO = estadoRepository.findAll().stream().map(EstadoDTO::new).collect(Collectors.toList());
		return estadosDTO;
	}
}
