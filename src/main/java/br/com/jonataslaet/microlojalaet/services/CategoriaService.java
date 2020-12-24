package br.com.jonataslaet.microlojalaet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.microlojalaet.domain.Categoria;
import br.com.jonataslaet.microlojalaet.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository cr;
	
	public Categoria buscar(Integer id) {
		Categoria categoria = cr.findById(id).get();
		return categoria;
	}
}
