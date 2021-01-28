package br.com.jonataslaet.microlojalaet.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.controllers.CidadeDTO;
import br.com.jonataslaet.microlojalaet.controllers.exceptions.ObjectNotFoundException;
import br.com.jonataslaet.microlojalaet.domain.Cidade;
import br.com.jonataslaet.microlojalaet.domain.Estado;
import br.com.jonataslaet.microlojalaet.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoService estadoService;
	
	public Cidade find(Integer idEstado, Integer id) {
		Estado estado = estadoService.find(idEstado);
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return cidade.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto " + Cidade.class.getSimpleName() + " de id = " + id + " não encontrado"));
	}
	
	public List<CidadeDTO> findAllByEstado(Integer id) {
		Estado estado = estadoService.find(id);
		List<CidadeDTO> cidadesDTO = cidadeRepository.findAllByEstado(id).stream().map(CidadeDTO::new).collect(Collectors.toList());
		return cidadesDTO;
	}
}
